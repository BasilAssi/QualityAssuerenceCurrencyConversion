///**
// * Created By: Basil Assi
// * ID Number: 1192308
// * Date: 5/18/2023
// * Time: 9:43 PM
// * Project Name: CurrencyConversion
// */
//
//package com.example.currencyconversion.currency;
//
//import com.mongodb.client.MongoCollection;
//import com.mongodb.client.MongoDatabase;
//import org.bson.Document;
//import com.mongodb.client.model.Filters;
//import com.mongodb.client.MongoClients;
//import com.mongodb.client.MongoClient;
//import org.springframework.data.domain.Example;
//import org.springframework.data.domain.Page;
//import org.springframework.data.domain.Pageable;
//import org.springframework.data.domain.Sort;
//import org.springframework.data.repository.query.FluentQuery;
//
//import java.util.List;
//import java.util.Map;
//import java.util.Optional;
//import java.util.function.Function;
//
//public class CurrencyRepositoryImpl implements CurrencyRepository {
//
//    private MongoDatabase database;
//    private MongoCollection<Document> collection;
//
//    public CurrencyRepositoryImpl() {
//        // Initialize your MongoDB connection and get the database and collection references
//        MongoClient mongoClient = MongoClients.create("mongodb://localhost:27017");
//        this.database = mongoClient.getDatabase("currency");
//        this.collection = database.getCollection("currency");
//        System.out.println("Connected Successfully");
//    }
//
//    @Override
//    public Currency findByFrom(String fromCurrency) {
//        Document query = new Document("base_code", fromCurrency);
//        Document result = collection.find(query).first();
//
//        if (result != null) {
//            // Map the document fields to your Currency object
//            Currency currency = new Currency();
//            currency.setFrom(result.getString("base_code"));
//            currency.setRates(result.get("conversion_rates", Map.class));
//
//            return currency;
//        } else {
//            throw new IllegalArgumentException("Currency not found");
//        }
//    }
//
//    @Override
//    public <S extends Currency> S insert(S entity) {
//        return null;
//    }
//
//    @Override
//    public <S extends Currency> List<S> insert(Iterable<S> entities) {
//        return null;
//    }
//
//    @Override
//    public <S extends Currency> Optional<S> findOne(Example<S> example) {
//        return Optional.empty();
//    }
//
//    @Override
//    public <S extends Currency> List<S> findAll(Example<S> example) {
//        return null;
//    }
//
//    @Override
//    public <S extends Currency> List<S> findAll(Example<S> example, Sort sort) {
//        return null;
//    }
//
//    @Override
//    public <S extends Currency> Page<S> findAll(Example<S> example, Pageable pageable) {
//        return null;
//    }
//
//    @Override
//    public <S extends Currency> long count(Example<S> example) {
//        return 0;
//    }
//
//    @Override
//    public <S extends Currency> boolean exists(Example<S> example) {
//        return false;
//    }
//
//    @Override
//    public <S extends Currency, R> R findBy(Example<S> example, Function<FluentQuery.FetchableFluentQuery<S>, R> queryFunction) {
//        return null;
//    }
//
//    @Override
//    public <S extends Currency> S save(S entity) {
//        return null;
//    }
//
//    @Override
//    public <S extends Currency> List<S> saveAll(Iterable<S> entities) {
//        return null;
//    }
//
//    @Override
//    public Optional<Currency> findById(String s) {
//        return Optional.empty();
//    }
//
//    @Override
//    public boolean existsById(String s) {
//        return false;
//    }
//
//    @Override
//    public List<Currency> findAll() {
//        return null;
//    }
//
//    @Override
//    public List<Currency> findAllById(Iterable<String> strings) {
//        return null;
//    }
//
//    @Override
//    public long count() {
//        return 0;
//    }
//
//    @Override
//    public void deleteById(String s) {
//
//    }
//
//    @Override
//    public void delete(Currency entity) {
//
//    }
//
//    @Override
//    public void deleteAllById(Iterable<? extends String> strings) {
//
//    }
//
//    @Override
//    public void deleteAll(Iterable<? extends Currency> entities) {
//
//    }
//
//    @Override
//    public void deleteAll() {
//
//    }
//
//    @Override
//    public List<Currency> findAll(Sort sort) {
//        return null;
//    }
//
//    @Override
//    public Page<Currency> findAll(Pageable pageable) {
//        return null;
//    }
//}
