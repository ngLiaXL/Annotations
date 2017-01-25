package ldroid.annotations;

import android.support.annotation.IntDef;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

public class Colors {
    @IntDef({RED, GREEN, YELLOW})
    @Retention(RetentionPolicy.SOURCE)
    public @interface LightColors{}

    public static final int RED = 0;
    public static final int GREEN = 1;
    public static final int YELLOW = 2;
}