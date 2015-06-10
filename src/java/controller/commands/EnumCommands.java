package controller.commands;

import controller.commands.humans.DischargeCommand;
import controller.commands.manipulations.DeleteManipulation;
import controller.commands.manipulations.ModifyManipulation;
import controller.commands.manipulations.AppointCommand;
import controller.commands.humans.RegistrationCommand;
import controller.commands.humans.LoginCommand;
import controller.commands.humans.PatientHistoryCommand;
import controller.commands.humans.LogoutCommand;
import controller.commands.humans.ChangePasswordCommand;
import controller.commands.humans.PatientRegistration;
import controller.commands.humans.ToRetirementCommand;


/**
 * The enumeration of all commands.
 */
public enum EnumCommands {
    
    LOGIN {
                {
                    this.command = new LoginCommand();
                }
            },
    LOGOUT {
                {
                    this.command = new LogoutCommand();
                }
            },
    REGISTRATION {
                {
                    this.command = new RegistrationCommand();
                }
            },
    APPOINT {
                {
                    this.command = new AppointCommand();
                }
            },
    DISCHARGE {
                {
                    this.command = new DischargeCommand();
                }
            },
    HISTORY {
                {
                    this.command = new PatientHistoryCommand();
                }
            },
    DELETE {
                {
                    this.command = new DeleteManipulation();
                }
            },
    MODIFY {
                {
                    this.command = new ModifyManipulation();
                }
            },
    PATIENT_REGISTRATION {
                {
                    this.command = new PatientRegistration();
                }
            },
    CHANGE_PASSWORD {
                {
                    this.command = new ChangePasswordCommand();
                }
            },
    RETIREMENT {
                {
                    this.command = new ToRetirementCommand();
                }
            },
    LANGUAGE {
                {
                    this.command = new LanguageCommand();
                }
            },
    JUMP {
                {
                    this.command = new JumpPageCommand();
                }
            };

    /**
     * Instance of command
     */
    Command  command;

    public Command getCurrentCommand() {
        return  command;
    }
}
