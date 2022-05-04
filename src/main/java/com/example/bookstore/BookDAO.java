package com.example.bookstore;

import java.sql.SQLException;
import java.util.List;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

public class BookDAO {

    public boolean insertBook(Book book) throws SQLException {
        boolean rowInserted = false;
        SqlSession session = null;

        try {
            SqlSessionFactory factory = ServiceLocator.getSessionFactory();
            session = factory.openSession();
            rowInserted = session.insert("insertBook", book) > 0;
            session.commit();

        } finally {

            if (session != null) {
                session.close();
            }
        }

        return rowInserted;
    }

    public List<Book> listAllBooks() throws SQLException {
        SqlSession session = null;
        List<Book> retrieveList = null;

        try {
            SqlSessionFactory factory = ServiceLocator.getSessionFactory();
            session = factory.openSession();
            retrieveList = session.selectList("selectAllBooks");

        } finally {

            if (session != null) {
                session.close();
            }
        }

        return retrieveList;
    }

    public boolean deleteBook(Book book) throws SQLException {
        boolean rowDeleted = false;
        SqlSession session = null;

        try {
            SqlSessionFactory factory = ServiceLocator.getSessionFactory();
            session = factory.openSession();
            rowDeleted = session.delete("deleteById", book.getId()) > 0;
            session.commit();

        } finally {

            if (session != null) {
                session.close();
            }
        }
        return rowDeleted;
    }

    public boolean updateBook(Book book) throws SQLException {
        boolean rowUpdated = false;
        SqlSession session = null;

        try {
            SqlSessionFactory factory = ServiceLocator.getSessionFactory();
            session = factory.openSession();
            rowUpdated = session.update("updateBook", book) > 0;
            session.commit();

        } finally {

            if (session != null) {
                session.close();
            }
        }
        return rowUpdated;
    }

    public Book getBook(int id) throws SQLException {
        SqlSession session = null;
        Book book = null;

        try {
            SqlSessionFactory factory = ServiceLocator.getSessionFactory();
            session = factory.openSession();
            book = session.selectOne("selectBook", id);

        } finally {

            if (session != null) {
                session.close();
            }
        }

        return book;
    }
}
