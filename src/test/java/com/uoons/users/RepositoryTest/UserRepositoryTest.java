package com.uoons.users.RepositoryTest;

import com.uoons.users.enitity.RoleEntity;
import com.uoons.users.enitity.UserEntity;
import com.uoons.users.repository.RoleRepository;
import com.uoons.users.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;

import java.util.UUID;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Rollback(value = false)
public class UserRepositoryTest {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private RoleRepository roleRepository;

    @Test
    public void testAddRoleToCustomer() {
        UserEntity customer = new UserEntity();
        customer.setEmail("kartik@gmail.com");
        customer.setPassword("kartik123");
        customer.setFirstName("Kartik");
        customer.setLastName("Khantal");
        customer.setMobileNo("9900990099");
        customer.setUserId(UUID.randomUUID().toString());
        RoleEntity roleCustomer = roleRepository.findByRoleName("CUSTOMER");
        customer.addRole(roleCustomer);

        UserEntity saveCustomer = userRepository.save(customer);
        assertThat(saveCustomer.getRole().size()).isEqualTo(1);

    }

    @Test
    public void testAddRoleToSeller() {
        UserEntity seller = new UserEntity();
        seller.setEmail("anshu@gmail.com");
        seller.setPassword("anshu123");
        seller.setFirstName("anshu");
        seller.setLastName("dwivedi");
        seller.setMobileNo("9900990099");
        seller.setUserId(UUID.randomUUID().toString());
        RoleEntity roleCustomer = roleRepository.findByRoleName("SELLER");
        seller.addRole(roleCustomer);

        UserEntity saveCustomer = userRepository.save(seller);
        assertThat(saveCustomer.getRole().size()).isEqualTo(1);

    }


     @Test
    public void testAddRoleToExistingCustomer() {
        UserEntity customer = userRepository.findById(2L).get();
        RoleEntity roleSeller = roleRepository.findByRoleName("SELLER");
        customer.addRole(roleSeller);

        UserEntity saveCustomer = userRepository.save(customer);
        assertThat(saveCustomer.getRole().size()).isEqualTo(2);

    }

    @Test
    public void testAddRoleToAdmin() {
        UserEntity admin = new UserEntity();
        admin.setEmail("yash@gmail.com");
        admin.setPassword("yash123");
        admin.setFirstName("Yash");
        admin.setLastName("Shrivastav");
        admin.setMobileNo("9900990099");
        admin.setUserId(UUID.randomUUID().toString());
        RoleEntity roleAdmin = roleRepository.findByRoleName("ADMIN");
        admin.addRole(roleAdmin);

        UserEntity saveCustomer = userRepository.save(admin);
        assertThat(saveCustomer.getRole().size()).isEqualTo(1);

    }


}
