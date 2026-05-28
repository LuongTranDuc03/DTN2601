package com.zalo.auto.backend.service;

import com.zalo.auto.dto.ImportError;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public interface IImportFile<T, E, K> {
    List<T> readFile(String path);
    void validation(T t, E e, List<ImportError> importErrors, List<K> entityList); // E là context, đưa vào map, list để check validation
    void saveAll(List<K> entityList);
    void exportFileError(List<ImportError> importErrors);

    // path: đường dẫn
    default String importFile(String path, E context) {
        File file = new File(path);
        if (!file.exists()) {
            return "File Not Found";
        }
        if (!path.toLowerCase().endsWith(".csv")) {
            return "File sai định dạng";
        }

        List<T> csvList = readFile(path);
        List<ImportError> importErrors = new ArrayList<>();
        List<K> entityList = new ArrayList<>();
        for (T entity : csvList) {
            validation(entity, context, importErrors, entityList);
        }

        // Lưu danh sách vào db
        saveAll(entityList);

        // xuất ra file lai
        exportFileError(importErrors);
        String message = "Import thành công " + entityList.size() + " entity, thất bại" + importErrors.size();

        return null;
    }
}
