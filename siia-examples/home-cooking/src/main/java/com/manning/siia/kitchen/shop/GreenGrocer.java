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

package com.manning.siia.kitchen.shop;

import org.springframework.integration.annotation.Transformer;

import com.manning.siia.kitchen.domain.GroceryBag;
import com.manning.siia.kitchen.domain.Ingredient;
import com.manning.siia.kitchen.domain.ShoppingList;
import com.manning.siia.kitchen.domain.Vegetable;

/** 
 * @author Iwein Fuld
 */
public class GreenGrocer {

	@Transformer
	public Vegetable sell(Ingredient ingredient) {
		return new Vegetable(ingredient.getName(), ingredient.getAmount());
	}

    @Transformer
    public GroceryBag<Vegetable> sell(ShoppingList shoppingList) {
        GroceryBag<Vegetable> groceryBag = new GroceryBag<Vegetable>();
        for (Ingredient ingredient : shoppingList.getItems()) {
            groceryBag.put(sell(ingredient));
        }
        return groceryBag;
    }

}
