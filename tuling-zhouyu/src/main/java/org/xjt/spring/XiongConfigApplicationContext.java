package org.xjt.spring;

import java.io.File;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.net.URL;
import java.util.ArrayList;
import java.util.concurrent.ConcurrentHashMap;

public class XiongConfigApplicationContext {
    private Class configClass;

    private ConcurrentHashMap<String,Object> singletonObjects = new ConcurrentHashMap<>();      //单例池
    private ConcurrentHashMap<String,BeanDefinition> beanDefinitionMap = new ConcurrentHashMap<>();      //beanDefinition池
    //将BeanPostProcessor放入到列表中
    private ArrayList<BeanPostProcessor> beanPostProcessorArrayList = new ArrayList<>();

    public XiongConfigApplicationContext(Class configClass) {
        this.configClass = configClass;
        //扫描包 将bean装入容器
        scanPackage(configClass);

        //@Autowire自动注入
        for (String beanName : beanDefinitionMap.keySet()) {
            BeanDefinition beanDefinition = beanDefinitionMap.get(beanName);
            if(beanDefinition.getScope().equals("singleton")){
                //单例bean 由容器创建并存入，prototype由用户调用时创建
                Object bean = createBean(beanName,beanDefinition);
                singletonObjects.put(beanName,bean);
            }
        }


    }

    public Object createBean(String beanName,BeanDefinition beanDefinition) {
        Class clazz = beanDefinition.getClazz();
        try {
            Object instance = clazz.getDeclaredConstructor().newInstance();

            //Autowire自动注入
            for (Field declaredField : clazz.getDeclaredFields()) {
                if(declaredField.isAnnotationPresent(Autowired.class)){
                    Object bean = getBean(declaredField.getName());
                    declaredField.setAccessible(true);
                    declaredField.set(instance,bean);
                }
            }
            //Aware回调 模拟BeanNameAware接口
            if(instance instanceof BeanNameAware){
                ((BeanNameAware) instance).setBeanName(beanName);
            }

            //初始化前置处理
            for (BeanPostProcessor beanPostProcessor : beanPostProcessorArrayList) {
                instance = beanPostProcessor.postProcessBeforeInitialization(instance, beanName);
            }

            //初始化
            if(instance instanceof InitializingBean){
                try {
                    ((InitializingBean) instance).afterPropertiesSet();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            //初始化后置处理
            for (BeanPostProcessor beanPostProcessor : beanPostProcessorArrayList) {
                instance = beanPostProcessor.postProcessAfterInitialization(instance, beanName);
            }


            return instance;
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }

        return null;
    }

    public void scanPackage(Class configClass) {
        //解析配置类(实例化时)
        //ComponentScan注解 -> 扫描路径 ->扫描类
        ComponentScan annotation = (ComponentScan) configClass.getDeclaredAnnotation(ComponentScan.class);
        String path = annotation.value();       //扫描路径
        path = path.replace(".","//");

        //系统类加载器
        ClassLoader classLoader = XiongConfigApplicationContext.class.getClassLoader();
        URL resource = classLoader.getResource(path);
        File file = new File(resource.getPath());
        if(file.isDirectory()){
            File[] files = file.listFiles();
            for (File f : files) {
                String absolutePath = f.getAbsolutePath();
                if(absolutePath.endsWith(".class")){
                    String classPackage = absolutePath.substring(absolutePath.indexOf("classes")+8, absolutePath.indexOf(".class")).replace("\\",".");
                    Class<?> clazz = null;
                    try {
                        clazz = classLoader.loadClass(classPackage);
                        if(clazz.isAnnotationPresent(Component.class)){
                            //有注解@Component 的类 就是一个bean
                            //bean解析类 ->BeanDefinition

                            //将beanPostProcessor实例对象放到列表中
                            if(BeanPostProcessor.class.isAssignableFrom(clazz)){
                                BeanPostProcessor postProcessorBean = (BeanPostProcessor) clazz.getDeclaredConstructor().newInstance();
                                beanPostProcessorArrayList.add(postProcessorBean);
                            }

                            Component componentAnnotaion = clazz.getDeclaredAnnotation(Component.class);
                            String beanName = componentAnnotaion.value();
                            BeanDefinition beanDefinition = new BeanDefinition();
                            beanDefinition.setClazz(clazz);
                            if(clazz.isAnnotationPresent(Scope.class)){
                                //存在@Scope注解 ，@Scope 或@Scope("singleton")都是单例模式
                                Scope scopeAnnotation = clazz.getDeclaredAnnotation(Scope.class);
                                beanDefinition.setScope(scopeAnnotation.value());
                            }else{
                                //没有@Scope 默认是singleton
                                beanDefinition.setScope("singleton");
                            }
                            //把beanDefinition放到池中
                            beanDefinitionMap.put(beanName,beanDefinition);
                        }
                    } catch (ClassNotFoundException | NoSuchMethodException e) {
                        e.printStackTrace();
                    } catch (IllegalAccessException e) {
                        e.printStackTrace();
                    } catch (InstantiationException e) {
                        e.printStackTrace();
                    } catch (InvocationTargetException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }


    public Object getBean(String beanName){
        if(beanDefinitionMap.containsKey(beanName)){
            BeanDefinition beanDefinition = beanDefinitionMap.get(beanName);
            if(beanDefinition.getScope().equals("singleton")){
                Object o = singletonObjects.get(beanName);
                return o;
            }else{
                //prototype
                Object bean = createBean(beanName,beanDefinition);
                return bean;
            }
        }else{
            //容器中没有beanName
            throw new NullPointerException();
        }
    }
}
