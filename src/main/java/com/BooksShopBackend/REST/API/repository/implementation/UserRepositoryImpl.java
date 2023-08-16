package com.BooksShopBackend.REST.API.repository.implementation;

import com.BooksShopBackend.REST.API.domain.User;
import com.BooksShopBackend.REST.API.exception.ApiException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.SqlParameter;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
import com.BooksShopBackend.REST.API.repository.UserRepository;

import java.util.Collection;
import java.util.Map;
import java.util.Objects;


@Repository
@RequiredArgsConstructor
@Slf4j
public class UserRepositoryImpl implements UserRepository<User> {
    private static  final  String COUNT_USER_EMAIL_QUERY = "";
    private static final String INSERT_USER_QUERY = "";
    private final NamedParameterJdbcTemplate jdbc;

    @Override
    public User create(User user) {
        //  Check the email is unique
        if(getemailCount(user.getEmail().trim().toLowerCase()) > 0 )throw new ApiException("Email already in use. Please use a diffrent email and try again.");
        //  Save new user
        try {
            KeyHolder holder = new GeneratedKeyHolder();
            SqlParameterSource paramaters = getSqlParameterSource(user);
            jdbc.update(INSERT_USER_QUERY, paramaters, holder);
            user.setId(Objects.requireNonNull(holder.getKey().longValue()));
        } catch (EmptyResultDataAccessException exception) {

        } catch (Exception exception)
        //  Add role to the user
        //  Send verification URL
        //  Save URL in verification table
        //  Send email to user with verification URL
        //  Return the newly created user
        //  IF any errors, throw exception with proper message
        return null;
    }

    private SqlParameterSource getSqlParameterSource(User user) {
    }


    @Override
    public Collection<User> list(int page, int pageSize) {
        return null;
    }

    @Override
    public User get(Long id) {
        return null;
    }

    @Override
    public User update(User data) {
        return null;
    }

    @Override
    public Boolean delete(Long id) {
        return null;
    }

    private Integer getemailCount(String email) {
        return jdbc.queryForObject(COUNT_USER_EMAIL_QUERY, Map.of("email", email), Integer.class);
    }
};
