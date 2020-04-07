package com.geekerstar.skills;

import org.junit.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * @author geekerstar
 * @date 2020/4/7 14:54
 * @description
 */
public class StringTest {

    @Test
    public void capitalize_test() throws Exception {
        assertThat(StringSnippets.capitalize("fooBar", false)).isEqualTo("FooBar");
        assertThat(StringSnippets.capitalize("fooBar", true)).isEqualTo("Foobar");
    }

    @Test
    public void capitalizeEveryWord_test() throws Exception {
        assertThat(StringSnippets.capitalizeEveryWord("hello world!")).isEqualTo("Hello World!");
    }

    @Test
    public void anagrams_test() throws Exception {
        List<String> anagrams = StringSnippets.anagrams("abc");
        assertThat(anagrams)
                .containsOnly("abc", "acb", "bac", "bca", "cab", "cba");
    }

    @Test
    public void byteSize_of_smiley_is_4() throws Exception {
        int length = StringSnippets.byteSize("\uD83D\uDE00");
        assertThat(length).isEqualTo(4);
    }

    @Test
    public void byteSize_of_hello_world_is_11() throws Exception {
        assertThat(StringSnippets.byteSize("Hello World")).isEqualTo(11);
    }

    @Test
    public void countVowels_test() throws Exception {
        assertThat(StringSnippets.countVowels("foobar")).isEqualTo(3);
    }

    @Test
    public void escapeRegex_test() throws Exception {
        assertThat(StringSnippets.escapeRegExp("(test)")).isEqualTo("\\Q(test)\\E");
    }

    @Test
    public void fromCamelCase_test() throws Exception {
        assertThat(StringSnippets.fromCamelCase("someJavaProperty", "_"))
                .isEqualTo("some_java_property");
        assertThat(StringSnippets.fromCamelCase("someDatabaseFieldName", " "))
                .isEqualTo("some database field name");
        assertThat(StringSnippets.fromCamelCase("someLabelThatNeedsToBeCamelized", "-"))
                .isEqualTo("some-label-that-needs-to-be-camelized");
    }

    @Test
    public void isAbsoluteUrl_test() throws Exception {
        assertThat(StringSnippets.isAbsoluteUrl("https://google.com")).isTrue();
        assertThat(StringSnippets.isAbsoluteUrl("ftp://www.myserver.net")).isTrue();
        assertThat(StringSnippets.isAbsoluteUrl("/foo/bar")).isFalse();
    }

    @Test
    public void isLowerCase_test() throws Exception {
        assertThat(StringSnippets.isLowerCase("abc")).isTrue();
        assertThat(StringSnippets.isLowerCase("a3@$")).isTrue();
        assertThat(StringSnippets.isLowerCase("Ab4")).isFalse();
    }

    @Test
    public void mask_test() throws Exception {
        assertThat(StringSnippets.mask("1234567890", 4, "*")).isEqualTo("******7890");
        assertThat(StringSnippets.mask("1234567890", 3, "*")).isEqualTo("*******890");
        assertThat(StringSnippets.mask("1234567890", -4, "*")).isEqualTo("1234******");
    }

    @Test
    public void palindrome_test() throws Exception {
        assertThat(StringSnippets.isPalindrome("taco cat")).isTrue();
        assertThat(StringSnippets.isPalindrome("abc")).isFalse();
    }

    @Test
    public void reverseString_test() throws Exception {
        assertThat(StringSnippets.reverseString("foobar")).isEqualTo("raboof");
    }

    @Test
    public void sortCharactersInString_test() throws Exception {
        assertThat(StringSnippets.sortCharactersInString("cabbage")).isEqualTo("aabbceg");
    }

    @Test
    public void splitLines_test() throws Exception {
        assertThat(StringSnippets.splitLines("This\nis a\nmultiline\nstring.\n"))
                .isEqualTo(new String[]{
                        "This",
                        "is a",
                        "multiline",
                        "string."
                });
    }

    @Test
    public void toCamelCase_test() throws Exception {
        assertThat(StringSnippets.toCamelCase("some_database_field_name")).isEqualTo("someDatabaseFieldName");
        assertThat(StringSnippets.toCamelCase("Some label that needs to be camelized")).isEqualTo("someLabelThatNeedsToBeCamelized");
        assertThat(StringSnippets.toCamelCase("some-java-property")).isEqualTo("someJavaProperty");
        assertThat(StringSnippets.toCamelCase("some-mixed_string with spaces_underscores-and-hyphens")).isEqualTo("someMixedStringWithSpacesUnderscoresAndHyphens");
    }

    @Test
    public void toKebabCase_test() throws Exception {
        assertThat(StringSnippets.toKebabCase("camelCase")).isEqualTo("camel-case");
        assertThat(StringSnippets.toKebabCase("some text")).isEqualTo("some-text");
        assertThat(StringSnippets.toKebabCase("some-mixed_string With spaces_underscores-and-hyphens")).isEqualTo("some-mixed-string-with-spaces-underscores-and-hyphens");
        assertThat(StringSnippets.toKebabCase("AllThe-small Things")).isEqualTo("all-the-small-things");
        assertThat(StringSnippets.toKebabCase("IAmListeningToFMWhileLoadingDifferentURLOnMyBrowserAndAlsoEditingXMLAndHTML")).isEqualTo("i-am-listening-to-fm-while-loading-different-url-on-my-browser-and-also-editing-xml-and-html");
    }

    @Test
    public void toSnakeCase_test() throws Exception {
        assertThat(StringSnippets.toSnakeCase("camelCase")).isEqualTo("camel_case");
        assertThat(StringSnippets.toSnakeCase("some text")).isEqualTo("some_text");
        assertThat(StringSnippets.toSnakeCase("some-mixed_string With spaces_underscores-and-hyphens")).isEqualTo("some_mixed_string_with_spaces_underscores_and_hyphens");
        assertThat(StringSnippets.toSnakeCase("AllThe-small Things")).isEqualTo("all_the_small_things");
        assertThat(StringSnippets.toSnakeCase("IAmListeningToFMWhileLoadingDifferentURLOnMyBrowserAndAlsoEditingXMLAndHTML")).isEqualTo("i_am_listening_to_fm_while_loading_different_url_on_my_browser_and_also_editing_xml_and_html");
    }

    @Test
    public void truncateString_test() throws Exception {
        assertThat(StringSnippets.truncateString("boomerang", 7)).isEqualTo("boom...");
    }

    @Test
    public void words_test() throws Exception {
        assertThat(StringSnippets.words("I love java!!")).isEqualTo(new String[]{"I", "love", "java"});
        assertThat(StringSnippets.words("Kotlin, Java & LemonTea")).isEqualTo(new String[]{"Kotlin", "Java", "LemonTea"});
    }

    @Test
    public void isNumeric_test() throws Exception {
        assertThat(StringSnippets.isNumeric("123")).isTrue();
        assertThat(StringSnippets.isNumeric("abc")).isFalse();
        assertThat(StringSnippets.isNumeric("")).isFalse();
    }

    @Test
    public void stringToIntegers_test() throws Exception {
        int[] intArray = StringSnippets.stringToIntegers("1 2 3 4 5");
        assertThat(intArray).isEqualTo(new int[]{1, 2, 3, 4, 5});
    }

    @Test
    public void randomInts_test() throws Exception {
        assertThat(StringSnippets.randomInts(5, 100, 200)).hasSize(5);
    }
}
