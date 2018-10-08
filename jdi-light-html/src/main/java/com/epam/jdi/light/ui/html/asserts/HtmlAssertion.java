package com.epam.jdi.light.ui.html.asserts;

import com.epam.jdi.light.asserts.IsAssert;
import com.epam.jdi.light.ui.html.base.HtmlElement;
import org.hamcrest.Matcher;

import static com.epam.jdi.light.common.Exceptions.exception;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

public class HtmlAssertion extends IsAssert implements ImageAssert, TextAssert, LinkAssert {

    HtmlElement html;
    public HtmlAssertion(HtmlElement html) { super(html); this.html = html; }

    public void src(Matcher<String> condition) { assertThat(html.getSrc(), condition); }
    public void alt(Matcher<String> condition) { assertThat(html.getAlt(), condition); }
    public void ref(Matcher<String> condition) { assertThat(html.getRef(), condition); }

    public void height(int value) {
        int height;
        try {
            height = Integer.parseInt(html.getHeight());
        } catch (Exception ex) { throw exception("Can't parse 'height' attribute '"+ html.getHeight()+"' to integer"); }
        assertThat(height, is(value));
    }
    public void width(int value) {
        int width;
        try {
            width = Integer.parseInt(html.getWidth());
        } catch (Exception ex) { throw exception("Can't parse 'width' attribute '"+ html.getWidth()+"' to integer"); }
        assertThat(width, is(value));
    }

}