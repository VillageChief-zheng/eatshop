package com.stateunion.eatshop.util;

import android.app.Activity;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by admini on 2017/11/6.
 */

public class ActivityTaskManager  <T extends Activity> extends HashSet<T> {
    private static final long serialVersionUID = -9211054184477813134L;

    private ActivityTaskManager() {

    }

    public static ActivityTaskManager<Activity> getInstance() {
        if (activities == null) {
            synchronized (ActivityTaskManager.class) {
                if (activities == null)
                    activities = new ActivityTaskManager<>();
            }
        }
        return activities;

    }
    private static ActivityTaskManager<Activity> activities;
    public void finishAll() {
        for (Activity a : this) {
            if (a == null) continue;
            a.finish();
        }
    }

    public void finishActiviy(Class cla) {
        for (Activity a : this) {
            if (a == null) continue;
            if (a.getClass().equals(cla)) {
                a.finish();
            }
        }
    }

    public void setResult(int result, Class... cla) {
        for (Activity a : this) {
            if (a == null) continue;
            for (int i = 0; i < cla.length; i++) {
                if (a.getClass() == cla[i]) {
                    a.setResult(result);
                    break;
                }
            }
        }
    }

    public void finishOther(Class cla) {
        for (Activity a : this) {
            if (a == null) continue;
            if (a.getClass() != cla) {
                a.finish();
            }
        }
    }

    public void finishOther(Set<Class> classes, int resultCode) {
        for (Activity a : this) {
            if (a == null) continue;
            if (!classes.contains(a.getClass())) {
                a.finish();
                a.setResult(resultCode);
            }
        }
    }

    public void finishOther(int resultCode, Class... cls) {
        for (Activity a : this) {
            if (a == null) continue;
            boolean isContains = false;
            for (int i = 0; i < cls.length; i++) {
                if (cls[i] == a.getClass()) {
                    isContains = true;
                    break;
                }
            }
            if (!isContains) {
                a.setResult(resultCode);
                a.finish();
            }
        }
    }
}
