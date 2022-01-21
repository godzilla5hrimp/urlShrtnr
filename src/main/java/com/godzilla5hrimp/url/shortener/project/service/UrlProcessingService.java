package com.godzilla5hrimp.url.shortener.project.service;

import com.godzilla5hrimp.url.shortener.project.model.UrlModel;
import com.godzilla5hrimp.url.shortener.project.repository.UrlModelRepository;
import com.godzilla5hrimp.url.shortener.project.repository.UserModelRepository;
import com.godzilla5hrimp.url.shortener.project.utils.CharsIdConverter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

@Service
public class UrlProcessingService {

    private static Logger logger = LoggerFactory.getLogger(UrlProcessingService.class);

    @Autowired
    UrlModelRepository urlModelRepository;

    //TODO: Add users
    @Autowired
    UserModelRepository userModelRepository;

    @Autowired
    CharsIdConverter charsIdConverter;

    /**
     * Generating shortened URL.
     * @param originalUrl - original URL that was input by user.
     * @return
     */
    public String generateUrl(String originalUrl, Long userId) {
        if(!originalUrl.isEmpty()) {
            UrlModel url = new UrlModel();
            url.setUrlOr(originalUrl);
            Long urlHash = Long.valueOf(url.hashCode()) + userId;
            if(urlModelRepository.findByUrlHash(urlHash) == null) {
                url.setUrlHash(urlHash);
                String urlShrt = createUniqueID(url.getUrlHash());
                url.setUrlShrt(urlShrt);
                url.setNmbShrt(url.getNmbShrt() + 1);
                url.setUserId(userId);
                urlModelRepository.save(url);
                logger.info("Entity was saved: urlModel: {};", url.toString());
                return urlShrt;
            } else {
                UrlModel foundUrl = urlModelRepository.findByUrlHash(urlHash);
                foundUrl.setNmbShrt(foundUrl.getNmbShrt() + 1);
                urlModelRepository.save(foundUrl);
                logger.info("Entity was updated: urlModel:{};", foundUrl.toString());
                return foundUrl.getUrlShrt();
            }
        }
        return null;
    }

    /**
     * Get info of number visited from shortened URL version.
     * @param shortUrl - shortened  URL.
     * @return
     */
    public Long getTimesWasVisited(String shortUrl) {
        Long urlHash = getDictionaryKeyFromUniqueID(shortUrl);
        UrlModel foundUrlModel = urlModelRepository.findByUrlHash(urlHash);
        return foundUrlModel.getNmbVis();
    }

    /**
     * Get info of number generated from shortened URL version.
     * @param shortUrl - shortened  URL.
     * @return
     */
    public Long getTimesWasShortened(String shortUrl) {
        Long urlHash = getDictionaryKeyFromUniqueID(shortUrl);
        UrlModel foundUrlModel = urlModelRepository.findByUrlHash(urlHash);
        return foundUrlModel.getNmbShrt();
    }

    /**
     * Get original URL input by user from short version.
     * @param shortUrl - shortened  URL.
     * @return
     */
    public String getOriginalUrl(String shortUrl) {
        Long urlHash = getDictionaryKeyFromUniqueID(shortUrl);
        UrlModel foundUrlModel = urlModelRepository.findByUrlHash(urlHash);
        logger.info("Found original entity {} by its hash: {}", foundUrlModel.toString(), urlHash);
        return foundUrlModel.getUrlOr();
    }

    /**
     * Add shortened url counter.
     * @param visitedUrl - shortened URL.
     */
    public void addVisited(String visitedUrl) {
        UrlModel foundUrlModel = urlModelRepository.findByUrlShrt(visitedUrl);
        foundUrlModel.setNmbVis(foundUrlModel.getNmbVis() + 1);
        logger.info("Visited counter of entity {} was incremented by one!", foundUrlModel.toString());
        urlModelRepository.save(foundUrlModel);
    }

    /**
     * Generate shortened URL version based on UrlModel hash.
     * @param id - urlModel hash.
     * @return
     */
    public String createUniqueID(Long id) {
        List<Integer> base62ID = convertBase10ToBase62ID(id);
        StringBuilder uniqueURLID = new StringBuilder();
        for (int digit: base62ID) {
            uniqueURLID.append(charsIdConverter.indexToCharTable.get(digit));
        }
        return uniqueURLID.toString();
    }

    /**
     * Convert hash to Base62.
     * @param id - urlModel hash.
     * @return
     */
    private List<Integer> convertBase10ToBase62ID(Long id) {
        List<Integer> digits = new LinkedList<>();
        while(id > 0) {
            int remainder = (int)(id % 62);
            ((LinkedList<Integer>) digits).addFirst(remainder);
            id /= 62;
        }
        return digits;
    }

    /**
     * Generate urlModel hash back from shortened URL.
     * @param uniqueID - shortened URL.
     * @return
     */
    public Long getDictionaryKeyFromUniqueID(String uniqueID) {
        List<Character> base62Number = new ArrayList<>();
        for (int i = 0; i < uniqueID.length(); ++i) {
            base62Number.add(uniqueID.charAt(i));
        }
        Long dictionaryKey = convertBase62ToBase10ID(base62Number);
        return dictionaryKey;
    }

    /**
     * Get hash from the URL by picking specific chars from Base62.
     * @param ids - shortened URL in List<Character> representation.
     * @return
     */
    private Long convertBase62ToBase10ID(List<Character> ids) {
        long id = 0L;
        int exp = ids.size() - 1;
        for (int i = 0; i < ids.size(); ++i, --exp) {
            int base10 = charsIdConverter.charToIndexTable.get(ids.get(i));
            id += (base10 * Math.pow(62.0, exp));
        }
        return id;
    }

}


