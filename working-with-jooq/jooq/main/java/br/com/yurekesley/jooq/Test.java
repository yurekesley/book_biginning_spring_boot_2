/*
 * This file is generated by jOOQ.
 */
package br.com.yurekesley.jooq;


import br.com.yurekesley.jooq.tables.Comments;
import br.com.yurekesley.jooq.tables.Posts;

import java.util.Arrays;
import java.util.List;

import org.jooq.Catalog;
import org.jooq.Table;
import org.jooq.impl.SchemaImpl;


/**
 * This class is generated by jOOQ.
 */
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class Test extends SchemaImpl {

    private static final long serialVersionUID = -1879391814;

    /**
     * The reference instance of <code>test</code>
     */
    public static final Test TEST = new Test();

    /**
     * The table <code>test.COMMENTS</code>.
     */
    public final Comments COMMENTS = Comments.COMMENTS;

    /**
     * The table <code>test.POSTS</code>.
     */
    public final Posts POSTS = Posts.POSTS;

    /**
     * No further instances allowed
     */
    private Test() {
        super("test", null);
    }


    @Override
    public Catalog getCatalog() {
        return DefaultCatalog.DEFAULT_CATALOG;
    }

    @Override
    public final List<Table<?>> getTables() {
        return Arrays.<Table<?>>asList(
            Comments.COMMENTS,
            Posts.POSTS);
    }
}
