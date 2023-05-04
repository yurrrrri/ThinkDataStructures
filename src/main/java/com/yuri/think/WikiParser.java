package com.yuri.think;

import org.jsoup.nodes.Element;
import org.jsoup.nodes.Node;
import org.jsoup.nodes.TextNode;
import org.jsoup.select.Elements;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.StringTokenizer;

public class WikiParser {

    private Elements paragraphs;
    private Deque<String> parenthesisStack;

    public WikiParser(Elements paragraphs) {
        this.paragraphs = paragraphs;
        this.parenthesisStack = new ArrayDeque<>();
    }

    public Element findFirstLink() {
        for(Element paragraph : paragraphs) {
            Element firstList = findFirstLinkParams(paragraph);

            if(firstList != null) return firstList;

            if(!parenthesisStack.isEmpty()) {
                System.err.println("Warning : unbalanced parentheses.");
            }
        }

        return null;
    }

    private Element findFirstLinkParams(Node root) {
        Iterable<Node> nt = new WikiNodeIterable(root);

        for(Node node : nt) {
            if(node instanceof TextNode) {
                processTextNode((TextNode) node);
            }
            if(node instanceof Element) {
                Element firstLink = processElement((Element) node);
                if(firstLink != null) return firstLink;
            }
        }

        return null;
    }

    private void processTextNode(TextNode node) {
        StringTokenizer st = new StringTokenizer(node.text(), " ()", true);
        while(st.hasMoreTokens()) {
            String token = st.nextToken();

            if(token.equals("(")) parenthesisStack.push(token);
            if(token.equals(")")) {
                if(parenthesisStack.isEmpty()) {
                    System.err.println("Warning : unbalanced parentheses.");
                }
                parenthesisStack.pop();
            }
        }
    }

    private Element processElement(Element element) {
        if(validLink(element)) return element;
        return null;
    }

    private boolean validLink(Element element) {
        if(!element.tagName().equals("a")) return false;
        if(isItalic(element)) return false;
        if(isInParens(element)) return false;
        if(startsWith(element, "#")) return false;
        if(startsWith(element, "/wiki/Help:")) return false;
        return true;
    }

    private boolean isItalic(Element start) {
        for(Element elt = start; elt != null; elt = elt.parent()) {
            if(elt.tagName().equals("i") || elt.tagName().equals("em")) return true;
        }
        return false;
    }

    private boolean isInParens(Element elt) {
        return !parenthesisStack.isEmpty();
    }

    private boolean startsWith(Element element, String str) {
        return element.attr("href").startsWith(str);
    }

}
