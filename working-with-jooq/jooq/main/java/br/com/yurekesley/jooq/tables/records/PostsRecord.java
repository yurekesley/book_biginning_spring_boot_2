/*
 * This file is generated by jOOQ.
 */
package br.com.yurekesley.jooq.tables.records;


import br.com.yurekesley.jooq.tables.Posts;

import java.time.LocalDateTime;

import org.jooq.Field;
import org.jooq.Record1;
import org.jooq.Record4;
import org.jooq.Row4;
import org.jooq.impl.UpdatableRecordImpl;


/**
 * This class is generated by jOOQ.
 */
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class PostsRecord extends UpdatableRecordImpl<PostsRecord> implements Record4<Integer, String, String, LocalDateTime> {

    private static final long serialVersionUID = -1061699994;

    /**
     * Setter for <code>test.POSTS.ID</code>.
     */
    public void setId(Integer value) {
        set(0, value);
    }

    /**
     * Getter for <code>test.POSTS.ID</code>.
     */
    public Integer getId() {
        return (Integer) get(0);
    }

    /**
     * Setter for <code>test.POSTS.TITLE</code>.
     */
    public void setTitle(String value) {
        set(1, value);
    }

    /**
     * Getter for <code>test.POSTS.TITLE</code>.
     */
    public String getTitle() {
        return (String) get(1);
    }

    /**
     * Setter for <code>test.POSTS.CONTENT</code>.
     */
    public void setContent(String value) {
        set(2, value);
    }

    /**
     * Getter for <code>test.POSTS.CONTENT</code>.
     */
    public String getContent() {
        return (String) get(2);
    }

    /**
     * Setter for <code>test.POSTS.CREATED_ON</code>.
     */
    public void setCreatedOn(LocalDateTime value) {
        set(3, value);
    }

    /**
     * Getter for <code>test.POSTS.CREATED_ON</code>.
     */
    public LocalDateTime getCreatedOn() {
        return (LocalDateTime) get(3);
    }

    // -------------------------------------------------------------------------
    // Primary key information
    // -------------------------------------------------------------------------

    @Override
    public Record1<Integer> key() {
        return (Record1) super.key();
    }

    // -------------------------------------------------------------------------
    // Record4 type implementation
    // -------------------------------------------------------------------------

    @Override
    public Row4<Integer, String, String, LocalDateTime> fieldsRow() {
        return (Row4) super.fieldsRow();
    }

    @Override
    public Row4<Integer, String, String, LocalDateTime> valuesRow() {
        return (Row4) super.valuesRow();
    }

    @Override
    public Field<Integer> field1() {
        return Posts.POSTS.ID;
    }

    @Override
    public Field<String> field2() {
        return Posts.POSTS.TITLE;
    }

    @Override
    public Field<String> field3() {
        return Posts.POSTS.CONTENT;
    }

    @Override
    public Field<LocalDateTime> field4() {
        return Posts.POSTS.CREATED_ON;
    }

    @Override
    public Integer component1() {
        return getId();
    }

    @Override
    public String component2() {
        return getTitle();
    }

    @Override
    public String component3() {
        return getContent();
    }

    @Override
    public LocalDateTime component4() {
        return getCreatedOn();
    }

    @Override
    public Integer value1() {
        return getId();
    }

    @Override
    public String value2() {
        return getTitle();
    }

    @Override
    public String value3() {
        return getContent();
    }

    @Override
    public LocalDateTime value4() {
        return getCreatedOn();
    }

    @Override
    public PostsRecord value1(Integer value) {
        setId(value);
        return this;
    }

    @Override
    public PostsRecord value2(String value) {
        setTitle(value);
        return this;
    }

    @Override
    public PostsRecord value3(String value) {
        setContent(value);
        return this;
    }

    @Override
    public PostsRecord value4(LocalDateTime value) {
        setCreatedOn(value);
        return this;
    }

    @Override
    public PostsRecord values(Integer value1, String value2, String value3, LocalDateTime value4) {
        value1(value1);
        value2(value2);
        value3(value3);
        value4(value4);
        return this;
    }

    // -------------------------------------------------------------------------
    // Constructors
    // -------------------------------------------------------------------------

    /**
     * Create a detached PostsRecord
     */
    public PostsRecord() {
        super(Posts.POSTS);
    }

    /**
     * Create a detached, initialised PostsRecord
     */
    public PostsRecord(Integer id, String title, String content, LocalDateTime createdOn) {
        super(Posts.POSTS);

        set(0, id);
        set(1, title);
        set(2, content);
        set(3, createdOn);
    }
}
