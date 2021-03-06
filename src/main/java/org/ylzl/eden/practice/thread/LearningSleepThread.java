/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
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

package org.ylzl.eden.practice.thread;

/**
 * TODO
 *
 * @author gyl
 * @since 2.0.0
 */
public class LearningSleepThread {

  private static Object lock = new Object();

  public static void main(String[] args) throws InterruptedException {
    new Thread(
            () -> {
              synchronized (lock) {
                System.out.println("线程 1 准备睡眠");
                try {
                  Thread.sleep(2000L);
                  System.out.println("线程 1 醒来了");
                } catch (InterruptedException e) {
                  e.printStackTrace();
                }
                System.out.println("线程 1 执行完毕");
              }
            })
        .start();

    Thread.sleep(10000L);

    new Thread(
            () -> {
              System.out.println("线程 2 在线程 1 睡眠中执行");
              StringBuilder sb = new StringBuilder();
              for (int i = 0; i < 500000000L; i++) {
                sb.append("1");
                sb.setLength(0);
              }
              System.out.println("线程 2 执行完毕");
            })
        .start();
  }
}
