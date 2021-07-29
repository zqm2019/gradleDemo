package com.zqm.controller;

import com.google.common.reflect.TypeToken;
import com.zqm.vo.Person;

import java.lang.reflect.ParameterizedType;

/**
 * @describe:
 * @author:zqm
 */
public class RefectTestController<T,R> {
    private Class<T> clazz;
    private Class<R> clazzR;



    public RefectTestController() {
        TypeToken<T> classType = new TypeToken<T>(getClass()) {};
        clazz = (Class<T>) classType.getRawType();

        TypeToken<R> classTypeR = new TypeToken<R>(getClass()) {};
        clazzR = (Class<R>) classTypeR.getRawType();

        System.out.println(clazz);
        System.out.println(clazzR);

        Class <T> entityClass = (Class <T>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
        Class <R> entityClassR =  (Class <R>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[1];


        System.out.println(entityClass);
        System.out.println(entityClassR);

    }



    public static class GT1 extends RefectTestController<RegularExpressionController,ScheduleController> {
        public static void main(String[] args) {
            System.out.println(new GT1().getClass().getGenericSuperclass());
        }
    }


    public interface BaseDao<T>{

    }

    public static class BaseDaoImpl<T> implements BaseDao<T>{
        private Class<?> clazz;

        public BaseDaoImpl() {
            if (clazz == null) { // 获取泛型的Class对象
                clazz = ((Class<?>) (((ParameterizedType) (this.getClass().getGenericSuperclass())).getActualTypeArguments()[0]));
            }
            System.out.println(clazz);
        }

    }

    public static class PersonDaoImpl extends BaseDaoImpl<Person>{
        public static void main(String[] args) {
            System.out.println(new PersonDaoImpl());
        }
    }



}

