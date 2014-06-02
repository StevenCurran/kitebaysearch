package com.scurran.service;

import ch.ralscha.extdirectspring.annotation.ExtDirectMethod;
import com.google.common.base.Function;
import com.google.common.collect.Collections2;
import com.google.common.collect.ImmutableList;
import com.scurran.User;
import com.scurran.domain.MenuItem;
import com.scurran.domain.Product;
import com.scurran.util.PropertyOrderingFactory;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;

import static ch.ralscha.extdirectspring.annotation.ExtDirectMethodType.TREE_LOAD;

@Service
public class MenuLoadService {

    @ExtDirectMethod(TREE_LOAD)
    public ImmutableList<Node> getTree(String node) {

        if ("root".equals(node) || !StringUtils.hasText(node)) {
            Node root = new Node();
            root.id = "Criteria";
            root.text = "Criteria";
            root.leaf = false;
            root.expanded = true;

            ImmutableList.Builder<Node> builder = ImmutableList.builder();
            for (Product product : Product.values()) {
                Node child = new Node();
                child.id = product.name();
                child.text = product.getTerm();
                child.leaf = false;
                builder.add(child);
            }
            root.children = builder.build();

            return ImmutableList.<Node>builder().add(root).build();
        }
        /*

        List<User> users = PropertyOrderingFactory.createOrdering("lastName").sortedCopy(db.findAllFromDepartment(node));

        return ImmutableList.copyOf(Collections2.transform(users, new Function<User, Node>() {
            @Override
            public Node apply(User input) {
                Node n = new Node();
                n.id = input.getId();
                n.text = input.getLastName() + " " + input.getFirstName();
                n.leaf = true;
                return n;
            }
        }));
        */
        return null;
    }

    public static class Node {
        public String id;

        public String text;

        public boolean leaf;

        public boolean expanded;

        public MenuItem menuItem;

        public ImmutableList<Node> children;
    }

}