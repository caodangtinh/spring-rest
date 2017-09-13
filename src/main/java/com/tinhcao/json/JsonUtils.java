package com.tinhcao.json;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.core.util.DefaultPrettyPrinter;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.tinhcao.model.Account;
import org.springframework.stereotype.Component;

import java.io.*;
import java.util.List;

@Component(value = "jsonUtils")
public class JsonUtils {
    private static final File ACCOUNT_JSON_FILE = new File("account.json");

    /**
     * Read json file and return list Account
     *
     * @return List Account
     * @throws IOException IOException
     */
    public List<Account> getListAccountFromJsonFile() throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        TypeReference<List<Account>> typeReference = new TypeReference<List<Account>>() {
        };
        InputStream inputStream = new FileInputStream(ACCOUNT_JSON_FILE);
        return mapper.readValue(inputStream, typeReference);
    }

    /**
     * Write array to Json file
     *
     * @param accountList List of Account
     * @throws IOException throw when file not found
     */
    public void writeJsonToFile(List<Account> accountList) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.enable(SerializationFeature.INDENT_OUTPUT);
        ObjectWriter writer = objectMapper.writer(new DefaultPrettyPrinter());
        OutputStream outputStream = new FileOutputStream(ACCOUNT_JSON_FILE);
        writer.writeValue(outputStream, accountList);
    }
}
