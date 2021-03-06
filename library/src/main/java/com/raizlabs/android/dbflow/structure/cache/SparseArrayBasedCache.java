package com.raizlabs.android.dbflow.structure.cache;

import android.util.SparseArray;

import com.raizlabs.android.dbflow.config.FlowLog;
import com.raizlabs.android.dbflow.structure.Model;

/**
 * Description: A cache backed by a {@link android.util.SparseArray}
 */
public class SparseArrayBasedCache<ModelClass extends Model> extends ModelCache<ModelClass, SparseArray<ModelClass>> {

    /**
     * Constructs new instance with a {@link android.util.SparseArray} cache
     */
    public SparseArrayBasedCache() {
        super(new SparseArray<ModelClass>());
    }

    /**
     * Constructs new instance with a {@link android.util.SparseArray} cache
     *
     * @param initialCapacity The initial capacity of the sparse array
     */
    public SparseArrayBasedCache(int initialCapacity) {
        super(new SparseArray<ModelClass>(initialCapacity));
    }

    /**
     * Constructs new instance with the specified {@link java.util.List}
     *
     * @param sparseArray The sparse array to use.
     */
    public SparseArrayBasedCache(SparseArray<ModelClass> sparseArray) {
        super(sparseArray);
    }

    @Override
    public void addModel(Long id, ModelClass model) {
        synchronized (getCache()) {
            getCache().put(id.intValue(), model);
        }
    }

    @Override
    public ModelClass removeModel(Long id) {
        ModelClass model = get(id);
        synchronized (getCache()) {
            getCache().remove(id.intValue());
        }
        return model;
    }

    @Override
    public void clear() {
        synchronized (getCache()) {
            getCache().clear();
        }
    }

    @Override
    public void setCacheSize(int size) {
        FlowLog.log(FlowLog.Level.I, "The cache size for " + SparseArrayBasedCache.class.getSimpleName() + " is not re-configurable.");
    }

    @Override
    public ModelClass get(Long id) {
        return id == null ? null : getCache().get(id.intValue());
    }
}
