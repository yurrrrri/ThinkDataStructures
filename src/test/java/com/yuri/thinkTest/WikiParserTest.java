package com.yuri.thinkTest;

import com.yuri.think.WikiFetcher;
import com.yuri.think.WikiParser;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.assertj.core.api.Assertions.assertThat;

public class WikiParserTest {

    static final WikiFetcher wf = new WikiFetcher();

    @Test
    void testFindFirstLink1() throws IOException {
        String url = "https://en.wikipedia.org/wiki/Java_(programming_language)";
        String href = findFirstLink(url);
        assertThat(href).isEqualTo("/wiki/Programming_language");
    }

    @Test
    void testFindFirstLink2() throws IOException {
        String url = "https://en.wikipedia.org/wiki/Mathematics";
        String href = findFirstLink(url);
        assertThat(href).isEqualTo("/wiki/Quantity");
    }

    private String findFirstLink(String url) throws IOException {
        Elements paragraphs = wf.readWikipedia(url);
        WikiParser wp = new WikiParser(paragraphs);
        Element elt = wp.findFirstLink();
        String href = elt.attr("href");
        return href;
    }
}
