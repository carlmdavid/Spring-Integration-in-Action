/*
 * Copyright 2012 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.manning.siia.trip.diary;

import java.io.File;

import org.springframework.integration.file.filters.AbstractFileListFilter;

/**
 * @author Iwein Fuld
 */
public class RefuseWrittenByThisProcess extends AbstractFileListFilter<File> {
    private final String processName;

    public RefuseWrittenByThisProcess(String processName) {
        this.processName = processName;
    }


    @Override
    public boolean accept(File file) {
        return file.getName().endsWith(processName);
    }
}
