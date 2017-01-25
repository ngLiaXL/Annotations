package ldroid.annotations;

import android.support.annotation.NonNull;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * create by liangxl at 2017/1/25
 */

public class MainManager {


    public static final int RUN_SUCCESS = 1;
    public static final int RUN_ERROR = 2;

    Callback callback;


    public void register(Callback callback) {
        this.callback = callback;
    }

    public void run(int requestCode) {
        // ...
        onCallback(requestCode);
    }


    private void onCallback(int requestCode) {
        if (callback != null) {
//            callback.onError();
//            callback.onSuccess();
            // use annotation

            String[] error = {"error callback"};

            runAnnotatedMethods(requestCode, callback, error);
        }
    }


    @Retention(RetentionPolicy.RUNTIME)
    @Target(ElementType.METHOD)
    public @interface CallbackAnnotation {

        int value();

    }


    public interface Callback {
        void onError(String errorCode);

        void onSuccess();
    }


    private static void runAnnotatedMethods(int requestCode, @NonNull Object object, Object[] parameter) {
        Class clazz = object.getClass();

        for (Method method : clazz.getDeclaredMethods()) {
            if (method.isAnnotationPresent(CallbackAnnotation.class)) {
                CallbackAnnotation ann = method.getAnnotation(CallbackAnnotation.class);
                if (ann.value() == requestCode) {
                    try {
                        // Make method accessible if private
                        if (!method.isAccessible()) {
                            method.setAccessible(true);
                        }
                        if (method.getParameterTypes().length > 0) {
                            method.invoke(object, parameter);
                        } else {
                            method.invoke(object);
                        }

                    } catch (IllegalAccessException e) {
                    } catch (InvocationTargetException e) {
                    }
                }
            }
        }
    }
}
