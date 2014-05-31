/**
 * Copyright 2010-2012 Ralph Schaer <ralphschaer@gmail.com>
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.scurran.util;

import org.springframework.expression.Expression;
import org.springframework.expression.spel.standard.SpelExpressionParser;

import com.google.common.collect.Ordering;

public class PropertyOrdering<T> extends Ordering<T> {
	private final static SpelExpressionParser parser = new SpelExpressionParser();

	private final Expression readPropertyExpression;

	public PropertyOrdering(String property) {
		this.readPropertyExpression = parser.parseExpression(property);
	}

	@SuppressWarnings("unchecked")
	@Override
	public int compare(T o1, T o2) {
		Object left = readPropertyExpression.getValue(o1);
		Object right = readPropertyExpression.getValue(o2);

		if (left == right) {
			return 0;
		}
		if (left == null) {
			return -1;
		}
		if (right == null) {
			return 1;
		}

		return ((Comparable<Object>) left).compareTo(right);
	}

}
