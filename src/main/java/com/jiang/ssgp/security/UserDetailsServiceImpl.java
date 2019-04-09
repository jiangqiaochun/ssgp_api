package com.jiang.ssgp.security;

import com.jiang.ssgp.domain.po.Authority;
import com.jiang.ssgp.domain.po.Student;
import com.jiang.ssgp.domain.po.Teacher;
import com.jiang.ssgp.domain.po.User;
import com.jiang.ssgp.repository.StudentRepository;
import com.jiang.ssgp.repository.TeacherRepository;
import com.jiang.ssgp.repository.UserRepository;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * @author jqc
 * @create 2019-03-18 18:46
 */
@Service
public class UserDetailsServiceImpl implements UserDetailsService {
    private final UserRepository userRepository;
    private final StudentRepository studentRepository;
    private final TeacherRepository teacherRepository;

    public UserDetailsServiceImpl(UserRepository userRepository, StudentRepository studentRepository, TeacherRepository teacherRepository) {
        this.userRepository = userRepository;
        this.studentRepository = studentRepository;
        this.teacherRepository = teacherRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        User user = userRepository.findById(s).orElse(null);
        if (null != user) {
            Authority authority = new Authority();
            authority.setName(user.getCharacter());
            Set<Authority> set = new HashSet<>();
            set.add(authority);
            user.setAuthorities(set);
            List<GrantedAuthority> grantedAuthorities = user.getAuthorities().stream()
                    .map(authority1 -> new SimpleGrantedAuthority(authority1.getName()))
                    .collect(Collectors.toList());
            return new org.springframework.security.core.userdetails.User(user.getId(), user.getPassword(), grantedAuthorities);

        }
        Student student = studentRepository.findById(s).orElse(null);
        if( null != student) {
            Authority authority = new Authority();
            authority.setName("student");
            Set<Authority> set = new HashSet<>();
            set.add(authority);
            student.setAuthorities(set);
            List<GrantedAuthority> grantedAuthorities = student.getAuthorities().stream()
                    .map(authority1 -> new SimpleGrantedAuthority(authority1.getName()))
                    .collect(Collectors.toList());
            return new org.springframework.security.core.userdetails.User(student.getId(), student.getPassword(), grantedAuthorities);
        }
        Teacher teacher = teacherRepository.findById(s).orElse(null);
        if( null != teacher) {
            Authority authority = new Authority();
            authority.setName("teacher");
            Set<Authority> set = new HashSet<>();
            set.add(authority);
            teacher.setAuthorities(set);
            List<GrantedAuthority> grantedAuthorities = teacher.getAuthorities().stream()
                    .map(authority1 -> new SimpleGrantedAuthority(authority1.getName()))
                    .collect(Collectors.toList());
            return new org.springframework.security.core.userdetails.User(teacher.getId(), teacher.getPassword(), grantedAuthorities);
        }
        throw new UsernameNotFoundException("用户不存在！");

    }
}