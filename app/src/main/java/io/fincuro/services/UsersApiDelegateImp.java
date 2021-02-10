package io.fincuro.services;

import io.fincuro.api.UsersApiDelegate;
import io.fincuro.model.User;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.NativeWebRequest;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UsersApiDelegateImp implements UsersApiDelegate {
    List<User> users = new ArrayList<>();
    @Override
    public Optional<NativeWebRequest> getRequest() {
        return Optional.empty();
    }

    @Override
    public ResponseEntity<Void> addUser(User user) {
        users.add(user);
        return null;
    }

    @Override
    public ResponseEntity<List<User>> searchUser(String searchString, Integer skip, Integer limit) {
        if(searchString !=null && !searchString.isEmpty() && skip != null && limit !=null){
            return ResponseEntity.ok(users.stream().filter(item->item.getName().equals(searchString)).skip(skip).limit(limit).collect(Collectors.toList()));
        }
        if(searchString !=null && !searchString.isEmpty()){
            return ResponseEntity.ok(users.stream().filter(item->item.getName().equals(searchString)).collect(Collectors.toList()));
        }
        if (skip != null && limit != null) {
            return ResponseEntity.ok(users.stream().skip(skip).limit(limit).collect(Collectors.toList()));
        }
        return ResponseEntity.ok(users);
    }
}
