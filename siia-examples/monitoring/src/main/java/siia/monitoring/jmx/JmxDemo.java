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

package siia.monitoring.jmx;

import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.integration.support.MessageBuilder;
import org.springframework.messaging.MessageChannel;

/**
 * @author Mark Fisher
 */
public class JmxDemo {

	public static void main(String[] args) throws InterruptedException {
		ClassPathXmlApplicationContext context =
				new ClassPathXmlApplicationContext("context.xml", JmxDemo.class);
		MessageChannel channel = context.getBean("channel", MessageChannel.class);
		for (int i = 0; i < 1000; i++) {
			channel.send(MessageBuilder.withPayload(i + "").build());
			Thread.sleep(3000);
		}
	}

}