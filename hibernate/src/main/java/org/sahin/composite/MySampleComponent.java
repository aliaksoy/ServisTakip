package org.sahin.composite;

import javax.faces.component.UIComponentBase;
import javax.faces.component.FacesComponent;
import javax.faces.context.FacesContext;
import javax.faces.context.ResponseWriter;
import java.io.IOException;
@FacesComponent("org.sahin.composite.MySampleComponent")
public class MySampleComponent extends UIComponentBase{
  public String getFamily() {
    return "org.sahin.composite";
  }
  @Override
  public void encodeBegin(FacesContext context) throws IOException {
    ResponseWriter writer = context.getResponseWriter();
    writer.startElement("div", null);
    writer.write("hello");
    writer.endElement("div");
  }
}
 