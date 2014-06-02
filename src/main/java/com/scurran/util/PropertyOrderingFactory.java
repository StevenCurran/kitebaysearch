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

import ch.ralscha.extdirectspring.bean.GroupInfo;
import ch.ralscha.extdirectspring.bean.SortDirection;
import ch.ralscha.extdirectspring.bean.SortInfo;
import com.google.common.collect.Ordering;
import org.springframework.expression.ParseException;

import java.util.Collection;

public class PropertyOrderingFactory {

    public static <T> Ordering<T> createOrdering(String propertyName) {
        try {
            Ordering<T> ordering = new PropertyOrdering<>(propertyName);
            return ordering;
        } catch (ParseException e) {
            return null;
        }
    }

    public static <T> Ordering<T> createOrdering(String propertyName, SortDirection sortDirection) {
        try {
            Ordering<T> ordering = new PropertyOrdering<>(propertyName);

            if (sortDirection == SortDirection.DESCENDING) {
                ordering = ordering.reverse();
            }

            return ordering;
        } catch (ParseException e) {
            return null;
        }
    }

    public static <T> Ordering<T> createOrderingFromSorters(Collection<SortInfo> sortInfos) {
        Ordering<T> ordering = null;

        if (sortInfos != null) {
            for (SortInfo sorter : sortInfos) {
                Ordering<T> propertyOrdering = createOrdering(sorter.getProperty(), sorter.getDirection());
                if (ordering == null) {
                    ordering = propertyOrdering;
                } else {
                    ordering = ordering.compound(propertyOrdering);
                }
            }
        }

        return ordering;
    }

    public <T> Ordering<T> createOrderingFromGroups(Collection<GroupInfo> groupInfos) {
        Ordering<T> ordering = null;

        if (groupInfos != null) {
            for (GroupInfo group : groupInfos) {
                Ordering<T> propertyOrdering = createOrdering(group.getProperty(), group.getDirection());
                if (ordering == null) {
                    ordering = propertyOrdering;
                } else {
                    ordering = ordering.compound(propertyOrdering);
                }
            }
        }

        return ordering;
    }

}
