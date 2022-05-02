package com.uoons.users.RepositoryTest;

import com.uoons.users.enitity.RoleEntity;
import com.uoons.users.repository.RoleRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;

import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Rollback(value = false)
public class RoleRepositoryTest {
    @Autowired
    private RoleRepository roleRepository;

    @Test
    public void testCreateRoles()
    {
        RoleEntity customer=new RoleEntity("CUSTOMER");
        RoleEntity admin=new RoleEntity("ADMIN");
        RoleEntity seller=new RoleEntity("SELLER");
        roleRepository.saveAll(List.of(customer,admin,seller));
        List<RoleEntity> roleList=roleRepository.findAll();
        assertThat(roleList.size()).isEqualTo(3);
    }
}
