package com.valleon.rewardyourteacherapi.utilities;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import com.valleon.rewardyourteacherapi.infrastructure.exceptionHandlers.CustomNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Map;

@Component
@Slf4j
@RequiredArgsConstructor
public class CloudinaryService {

    private final Cloudinary cloudinary;

    private static File convertMultipartToFile(MultipartFile image) throws IOException{
        File convertedFile;
        String file = image.getOriginalFilename();

        assert file != null;

      try (  FileOutputStream fileOutputStream = new FileOutputStream(file)){
            convertedFile = new File(file);
            fileOutputStream.write(image.getBytes());
        }
      catch (IOException e) {
          throw new CustomNotFoundException("Error uploading image");
      }
      return convertedFile;
    }

    public String uploadImage(MultipartFile image) throws IOException{
        try{
            File uploadedFile = convertMultipartToFile(image);
            Map uploadResult = cloudinary.uploader().upload(uploadedFile,
                    ObjectUtils.asMap("use filename", true, "unique-filename", true));
            boolean isDeleted = uploadedFile.delete();

            if(isDeleted)
                log.info("File Deleted Successfully");
            else
                log.info("File does not exist");
            return uploadResult.get("url").toString();
        }catch (IOException e){
            throw new IOException(e);
        }
    }

}