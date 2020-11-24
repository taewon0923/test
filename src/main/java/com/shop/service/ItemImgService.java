package com.shop.service;

import com.shop.entity.ItemImg;
import com.shop.repository.ItemImgRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.FileOutputStream;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Transactional
public class ItemImgService {

    @Value("${itemImgLocation}")
    private String itemImgLocation;

    private final ItemImgRepository itemImgRepository;

    private String generateItemImgName(String fileName) {

        String extension = fileName.substring(fileName.lastIndexOf("."), fileName.length());
        UUID uuid = UUID.randomUUID();
        String itemImgName = uuid.toString() + extension;

        return itemImgName;
    }

    public void saveItemImg(ItemImg itemImg, MultipartFile itemImgFile) throws Exception{
        if(itemImg.getId() == null || !itemImgFile.isEmpty()) {
            //파일 업로드
            String originalImgName = itemImgFile.getOriginalFilename();
            String itemImgName = generateItemImgName(originalImgName);
            String imgPath = itemImgLocation + "/"+ itemImgName;
            byte[] data = itemImgFile.getBytes();
            FileOutputStream fos = new FileOutputStream(imgPath);
            fos.write(data);
            fos.close();

            //상품 이미지 정보 세팅
            itemImg.setImgName(itemImgName);
            itemImg.setOriImgName(originalImgName);
            itemImg.setImgUrl(imgPath);
            itemImgRepository.save(itemImg);
        }
    }

}
