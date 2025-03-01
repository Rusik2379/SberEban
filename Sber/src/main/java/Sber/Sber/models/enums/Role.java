package Sber.Sber.models.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@RequiredArgsConstructor

public enum Role {
    ROLE_USER(Collections.emptySet()),
    ROLE_DIRECTOR(Collections.emptySet()),
    ROLE_ADMIN(Set.of(Permission.ADMIN_READ, Permission.ADMIN_UPDATE, Permission.ADMIN_CREATE, Permission.ADMIN_DELETE));

    @Getter
    private final Set<Permission> permissionSet;


    public List<SimpleGrantedAuthority> getAuthorities() {

        var authoritiesList = getPermissionSet().stream()
                .map(permission -> new SimpleGrantedAuthority(permission.getPermissions()))
                .collect(Collectors.toList());

        authoritiesList.add(new SimpleGrantedAuthority("ROLE_" + this.name()));
        return authoritiesList;
    }
}
