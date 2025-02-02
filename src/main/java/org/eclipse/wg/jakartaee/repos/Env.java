/**
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements. See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership. The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License. You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied. See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */
package org.eclipse.wg.jakartaee.repos;

import org.kohsuke.github.GitHub;

import java.io.IOException;
import java.util.Optional;

public class Env {

    public static GitHub github() {

        final String user = Optional.ofNullable(System.getenv("GITHUB_USER"))
                .orElse(System.getenv("USER"));

        final String token = Optional.ofNullable(System.getenv("GITHUB_TOKEN"))
                .orElseThrow(() -> new IllegalStateException("GITHUB_TOKEN must be set as an env variable\nFollow instructions here: https://github.com/settings/tokens/new"));

        try {
            return GitHub.connect(user, token);
        } catch (IOException e) {
            throw new IllegalStateException("Unable to log into Github", e);
        }
    }
}
