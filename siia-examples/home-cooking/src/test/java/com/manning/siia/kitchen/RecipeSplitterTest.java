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

import static org.hamcrest.Matchers.instanceOf;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;
import static org.junit.Assert.assertThat;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.integration.support.MessageBuilder;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.PollableChannel;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.manning.siia.kitchen.domain.Amount;
import com.manning.siia.kitchen.domain.Grocery;
import com.manning.siia.kitchen.domain.Ingredient;
import com.manning.siia.kitchen.domain.Recipe;
import com.manning.siia.kitchen.domain.RecipeObjectMother;

/**
 * @author Iwein Fuld
 */
@ContextConfiguration(locations = {"/home-dinner-flow.xml", "/TEST-recipeSplitter.xml"})
@RunWith(SpringJUnit4ClassRunner.class)
public class RecipeSplitterTest {
	@Autowired
	MessageChannel recipes;

	@Autowired
	PollableChannel test;

	@Test
	public void shouldSplitRecipe() {
		Recipe recipe = RecipeObjectMother.friedEggRecipe();
		recipes.send(MessageBuilder.withPayload(recipe).build());
		receiveAndCheckIngredientMessage(new Grocery("egg", new Amount(1, Amount.Unit.PIECES)));
		receiveAndCheckIngredientMessage(new Grocery("butter", new Amount(20, Amount.Unit.GRAMS)));
	}

	private void receiveAndCheckIngredientMessage(final Grocery product) {
		final Message<Ingredient> message = (Message<Ingredient>) test.receive(2000);
		assertThat("Message was null", message, is(notNullValue()));
		assertThat(message.getHeaders().get("recipe"), is(instanceOf(Recipe.class)));
		assertThat(message.getPayload().isSatisfiedBy(product), is(true));
	}
}
