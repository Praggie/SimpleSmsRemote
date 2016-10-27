package tranquvis.simplesmsremote.CommandManagement.Commands;

import android.content.Context;

import org.intellij.lang.annotations.Language;

import tranquvis.simplesmsremote.CommandManagement.Command;
import tranquvis.simplesmsremote.CommandManagement.CommandExecResult;
import tranquvis.simplesmsremote.CommandManagement.CommandInstance;
import tranquvis.simplesmsremote.CommandManagement.Params.CommandParamOnOff;
import tranquvis.simplesmsremote.R;
import tranquvis.simplesmsremote.Utils.Device.MobileDataUtils;
import tranquvis.simplesmsremote.Utils.Regex.MatchType;
import tranquvis.simplesmsremote.Utils.Regex.PatternTreeNode;

/**
 * Created by Andreas Kaltenleitner on 27.10.2016.
 */
public class CommandSetMobileDataState extends Command
{
    private static final CommandParamOnOff PARAM_MOBILE_DATA_STATE =
            new CommandParamOnOff("mobile_data_state");

    @Language("RegExp")
    private static final String
            PATTERN_ROOT = GetPatternFromTemplate(PATTERN_TEMPLATE_SET_STATE_ON_OFF,
                "((mobile\\s+data)|(mobile\\s+internet))(\\s+connection)?");

    public CommandSetMobileDataState()
    {
        this.titleRes = R.string.command_title_set_mobile_data_state;
        this.syntaxDescList = new String[]{
                "enable mobile data",
                "disable mobile data"
        };
        this.patternTree = new PatternTreeNode(PARAM_MOBILE_DATA_STATE.getId(),
                PATTERN_ROOT,
                MatchType.DO_NOT_MATCH
        );
    }

    @Override
    protected void execute(Context context, CommandInstance commandInstance,
                           CommandExecResult result) throws Exception
    {
        MobileDataUtils.SetMobileDataState(context,
                commandInstance.getParam(PARAM_MOBILE_DATA_STATE));
    }
}