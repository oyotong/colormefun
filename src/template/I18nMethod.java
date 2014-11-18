package template;

import freemarker.template.TemplateMethodModel;
import freemarker.template.TemplateModelException;
import java.util.List;
import java.util.ResourceBundle;

public class I18nMethod
        implements TemplateMethodModel
{
    private ResourceBundle bundle;

    public I18nMethod(ResourceBundle bundle)
    {
        this.bundle = bundle;
    }

    public Object exec(List argList) throws TemplateModelException
    {
        if (argList.size() == 0)
        {
            throw new TemplateModelException("Wrong arguments!");
        }

        String message = this.bundle.getString((String)argList.get(0));

        for (int i = 0; i < argList.size() - 1; i++) {
            message = message.replace("{" + i + "}", (String)argList.get(i + 1));
        }

        return message;
    }
}