/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 *  (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.ylzl.eden.practice.caching;

import java.util.LinkedHashMap;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 基于 LinkedHashMap 实现 LRU 缓存淘汰算法
 *
 * @author gyl
 * @since 2.0.0
 */
public class LRULinkedHashMap<K, V> extends LinkedHashMap<K, V> {

  private final int maxCapacity;

  private static final float DEFAULT_LOAD_FACTOR = 0.75f;

  private final Lock lock = new ReentrantLock();

  public LRULinkedHashMap(int maxCapacity) {
    super(maxCapacity, DEFAULT_LOAD_FACTOR, true);
    this.maxCapacity = maxCapacity;
  }

  @Override
  protected boolean removeEldestEntry(java.util.Map.Entry<K, V> eldest) {
    return size() > maxCapacity;
  }

  @Override
  public boolean containsKey(Object key) {
    try {
      lock.lock();
      return super.containsKey(key);
    } finally {
      lock.unlock();
    }
  }

  @Override
  public V get(Object key) {
    try {
      lock.lock();
      return super.get(key);
    } finally {
      lock.unlock();
    }
  }

  @Override
  public V put(K key, V value) {
    try {
      lock.lock();
      return super.put(key, value);
    } finally {
      lock.unlock();
    }
  }

	@Override
  public int size() {
    try {
      lock.lock();
      return super.size();
    } finally {
      lock.unlock();
    }
  }

	@Override
  public void clear() {
    try {
      lock.lock();
      super.clear();
    } finally {
      lock.unlock();
    }
  }
}
