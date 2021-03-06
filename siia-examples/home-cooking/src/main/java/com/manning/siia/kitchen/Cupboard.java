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

package com.manning.siia.kitchen;

import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.integration.annotation.Filter;

import com.google.common.collect.Lists;
import com.manning.siia.kitchen.domain.Ingredient;
import com.manning.siia.kitchen.domain.Product;

/** 
 * @author Iwein Fuld
 */
public class Cupboard {

	private List<Product> stock = Lists.newArrayList();

	@PostConstruct
	public void loadContents() {
		// TODO
	}

	@Filter
	public boolean inStock(Ingredient ingredient) {
		// TODO
		return false;
	}
}
