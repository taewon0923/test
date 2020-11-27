package com.shop.service;

import com.shop.entity.ItemImg;
import com.shop.repository.ItemImgRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import org.thymeleaf.util.StringUtils;

import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional
public class ItemImgService {

    @Value("${itemImgLocation}")
    private String itemImgLocation;

    private final ItemImgRepository itemImgRepository;

    private final FileService fileService;

    public void saveItemImg(ItemImg itemImg, MultipartFile itemImgFile) throws Exception{

        if(itemImg.getId() == null) {

            String originalImgName = itemImgFile.getOriginalFilename();
            String itemImgName = "";
            String imgUrl = "";

            //파일 업로드
            if(!StringUtils.isEmpty(originalImgName)){
                itemImgName = fileService.uploadFile(itemImgLocation, originalImgName, itemImgFile.getBytes());
                imgUrl = itemImgLocation + "/" + itemImgName;
            }

            //상품 이미지 정보 세팅
            itemImg.setImgName(itemImgName);
            itemImg.setOriImgName(originalImgName);
            itemImg.setImgUrl(imgUrl);
            itemImgRepository.save(itemImg);
        } else if(!itemImgFile.isEmpty()){
            Optional<ItemImg> result = itemImgRepository.findById(itemImg.getId());
            if(result.isPresent()){
                ItemImg savedItemImg = result.get();
                String imgUrl = savedItemImg.getImgUrl();

                //기존 이미지 파일 삭제
                if(!StringUtils.isEmpty(savedItemImg.getImgName())) {
                    fileService.deleteFile(imgUrl);
                }

                String originalImgName = itemImgFile.getOriginalFilename();
                String itemImgName = fileService.uploadFile(itemImgLocation, originalImgName, itemImgFile.getBytes());
                imgUrl = itemImgLocation + "/" + itemImgName;

                savedItemImg.setOriImgName(originalImgName);
                savedItemImg.setImgName(itemImgName);
                savedItemImg.setImgUrl(imgUrl);
            }
        }
    }

}
