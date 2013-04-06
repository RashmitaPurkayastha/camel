/**
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
package org.apache.camel.impl.converter;

import org.apache.camel.spi.FactoryFinder;
import org.apache.camel.spi.Injector;
import org.apache.camel.spi.PackageScanClassResolver;

/**
 * Default implementation of a type converter registry used for
 * <a href="http://camel.apache.org/type-converter.html">type converters</a> in Camel.
 * <p/>
 * This implementation will load type converters up-front on startup.
 *
 * @version 
 */
public class DefaultTypeConverter extends BaseTypeConverterRegistry {

    public DefaultTypeConverter(PackageScanClassResolver resolver, Injector injector, FactoryFinder factoryFinder) {
        super(resolver, injector, factoryFinder);
    }

    @Override
    protected void doStart() throws Exception {
        super.doStart();
        // load type converters up front
        loadCoreTypeConverters();
        loadTypeConverters();

        // report how many type converters we have loaded
        log.info("Loaded {} type converters", typeMappings.size());
    }

}
