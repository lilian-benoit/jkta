/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 */
package org.eclipse.wg.jakartaee;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;

public class Jar {
    private final String name;
    private final List<Clazz> classes = new ArrayList<>();

    public Jar(final String name) {
        this.name = name;
    }

    public List<Clazz> getClasses() {
        return classes;
    }

    public String getName() {
        return name;
    }

    public List<String> getReferences() {
        final List<String> references = new ArrayList<>();
        for (final Clazz clazz : classes) {
            references.addAll(clazz.getReferences());
        }
        return references;
    }

    public List<String> getDistinctReferences() {
        final HashSet<String> references = new HashSet<>();

        for (final Clazz clazz : classes) {
            references.addAll(clazz.getReferences());
        }

        final List<String> strings = new ArrayList<>(references);
        Collections.sort(strings);
        return strings;
    }

    public boolean hasReferences() {
        for (final Clazz clazz : classes) {
            if (clazz.hasReferences()) return true;
        }
        return false;
    }

    @Override
    public String toString() {
        return "Jar{" +
                "name='" + name + '\'' +
                ", classes=" + classes.size() +
                '}';
    }
}