package DAO;

import java.util.List;

public interface DataImportExport<T> {
    // Exporter les données vers un fichier
    void exportData(List<T> data, String filePath);

    // Importer les données depuis un fichier
    List<T> importData(String filePath);
}