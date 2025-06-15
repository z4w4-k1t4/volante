package jp.co.volante.user;

import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import jakarta.persistence.EntityNotFoundException;
import jp.co.volante.user.dto.RequestUserDto;
import jp.co.volante.user.dto.ResponseUserDto;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    public List<ResponseUserDto> getUser(int page, int limit) {
        Pageable pageable = PageRequest.of(page, limit); // ページ番号, ページサイズ

        return userRepository.findAll(pageable)
                .stream()
                .limit(limit)
                .map(this::toResponseDto)
                .collect(Collectors.toList());
    }

    public ResponseUserDto getUserById(Long id) {
        return userRepository
                .findById(id)
                .map(this::toResponseDto)
                .orElseThrow(() -> new EntityNotFoundException("User not found"));
    }

    public ResponseUserDto getUserByUid(String uid) {
        return userRepository
                .findByUid(uid)
                .map(this::toResponseDto)
                .orElseThrow(() -> new EntityNotFoundException("User not found"));
    }

    public ResponseUserDto createUser(RequestUserDto dto, String uid) {
        User user = new User();
        user.setUid(dto.getUid());
        user.setDisplayName(dto.getDisplayName());
        user.setEmail(dto.getEmail());
        User saved = userRepository.save(user);
        return updateUserById(toRequestDto(saved), saved.getId(), uid);
    }

    public ResponseUserDto updateUserById(RequestUserDto dto, Long id, String updateUid) {
        User user = new User();
        ResponseUserDto originalUser = getUserById(id);
        ResponseUserDto updateUser = getUserByUid(updateUid);
        // ここの処理は共通化できそうだがあえてそのまま
        if (dto.getId() != null) {
            user.setId(dto.getId());
        } else {
            user.setId(originalUser.getId());
        }
        if (dto.getUid() != null) {
            user.setUid(dto.getUid());
        } else {
            user.setUid(originalUser.getUid());
        }
        if (dto.getDisplayName() != null) {
            user.setDisplayName(dto.getDisplayName());
        } else {
            user.setDisplayName(originalUser.getDisplayName());
        }
        if (dto.getEmail() != null) {
            user.setEmail(dto.getEmail());
        } else {
            user.setEmail(originalUser.getEmail());
        }
        if (dto.getCreateUserId() != null) {
            user.setCreateUserId(updateUser.getId());
        } else {
            if (originalUser.getCreateUserId() != null) {
                user.setCreateUserId(originalUser.getCreateUserId());
            } else {
                user.setCreateUserId(updateUser.getId());
            }
        }
        if (dto.getUpdateUserId() != null) {
            user.setUpdateUserId(updateUser.getId());
        } else {
            if (originalUser.getUpdateUserId() != null) {
                user.setUpdateUserId(originalUser.getUpdateUserId());
            } else {
                user.setUpdateUserId(updateUser.getId());
            }
        }
        user.setDeleteAt(originalUser.getDeleteAt());
        user.setDeleteUserId(originalUser.getDeleteUserId());
        User saved = userRepository.save(user);
        return toResponseDto(saved);
    }

    public ResponseUserDto updateUserByUid(RequestUserDto dto, String uid, String updateUid) {
        User user = new User();
        ResponseUserDto originalUser = getUserByUid(uid);
        ResponseUserDto updateUser = getUserByUid(updateUid);
        // ここの処理は共通化できそうだがあえてそのまま
        if (dto.getId() != null) {
            user.setId(dto.getId());
        } else {
            user.setId(originalUser.getId());
        }
        if (dto.getUid() != null) {
            user.setUid(dto.getUid());
        } else {
            user.setUid(originalUser.getUid());
        }
        if (dto.getDisplayName() != null) {
            user.setDisplayName(dto.getDisplayName());
        } else {
            user.setDisplayName(originalUser.getDisplayName());
        }
        if (dto.getEmail() != null) {
            user.setEmail(dto.getEmail());
        } else {
            user.setEmail(originalUser.getEmail());
        }
        if (dto.getCreateUserId() != null) {
            user.setCreateUserId(updateUser.getId());
        } else {
            if (originalUser.getCreateUserId() != null) {
                user.setCreateUserId(originalUser.getCreateUserId());
            } else {
                user.setCreateUserId(updateUser.getId());
            }
        }
        if (dto.getUpdateUserId() != null) {
            user.setUpdateUserId(updateUser.getId());
        } else {
            if (originalUser.getUpdateUserId() != null) {
                user.setUpdateUserId(originalUser.getUpdateUserId());
            } else {
                user.setUpdateUserId(updateUser.getId());
            }
        }
        user.setDeleteAt(originalUser.getDeleteAt());
        user.setDeleteUserId(originalUser.getDeleteUserId());
        User saved = userRepository.save(user);
        return toResponseDto(saved);
    }

    private RequestUserDto toRequestDto(User user) {
        RequestUserDto dto = new RequestUserDto();
        dto.setId(user.getId());
        dto.setUid(user.getUid());
        dto.setDisplayName(user.getDisplayName());
        dto.setEmail(user.getEmail());
        dto.setCreateUserId(user.getCreateUserId());
        dto.setUpdateUserId(user.getUpdateUserId());
        dto.setDeleteAt(user.getDeleteAt());
        dto.setDeleteUserId(user.getDeleteUserId());
        return dto;
    }

    private ResponseUserDto toResponseDto(User user) {
        ResponseUserDto dto = new ResponseUserDto();
        dto.setId(user.getId());
        dto.setUid(user.getUid());
        dto.setDisplayName(user.getDisplayName());
        dto.setEmail(user.getEmail());
        dto.setCreateAt(user.getCreateAt());
        dto.setCreateUserId(user.getCreateUserId());
        dto.setUpdateAt(user.getUpdateAt());
        dto.setUpdateUserId(user.getUpdateUserId());
        dto.setDeleteAt(user.getDeleteAt());
        dto.setDeleteUserId(user.getDeleteUserId());
        return dto;
    }
}
