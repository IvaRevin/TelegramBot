package telegram.operation;

import calculation.Calculator;
import calculation.PlusMinusService;
import enums.OperationEnum;
import fileProcessor.WordFileProcessorImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.telegram.telegrambots.extensions.bots.commandbot.commands.BotCommand;
import org.telegram.telegrambots.meta.api.methods.send.SendDocument;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.InputFile;
import org.telegram.telegrambots.meta.bots.AbsSender;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import telegram.Bot;


import java.io.FileInputStream;
import java.io.IOException;
import java.util.List;

abstract class OperationCommand extends BotCommand {

    private Logger logger = LoggerFactory.getLogger(OperationCommand.class);
    private PlusMinusService service;

    OperationCommand(String identifier, String description) {
        super(identifier, description);
        this.service = new PlusMinusService(new WordFileProcessorImpl(), new Calculator());
    }


    void sendAnswer(AbsSender absSender, Long chatId, List<OperationEnum> operations, String description,
                    String commandName, String userName) {
        try {
            absSender.execute(createDocument(chatId, operations, description));
        } catch (IOException | RuntimeException e) {
            logger.error(String.format("Ошибка %s. Команда %s. Пользователь: %s", e.getMessage(), commandName, userName));
            sendError(absSender, chatId, commandName, userName);
            e.printStackTrace();
        } catch (TelegramApiException e) {
            logger.error(String.format("Ошибка %s. Команда %s. Пользователь: %s", e.getMessage(), commandName, userName));
            e.printStackTrace();
        }
    }


    private SendDocument createDocument(Long chatId, List<OperationEnum> operations, String fileName) throws IOException {
        FileInputStream stream = service.getPlusMinusFile(operations, Bot.getUserSettings(chatId));
        SendDocument document = new SendDocument();
        document.setChatId(chatId.toString());
        document.setDocument(new InputFile(stream, String.format("%s.docx", fileName)));
        return document;
    }


    private void sendError(AbsSender absSender, Long chatId, String commandName, String userName) {
        try {
            absSender.execute(new SendMessage(chatId.toString(), "Похоже, я сломался. Попробуйте позже"));
        } catch (TelegramApiException e) {
            logger.error(String.format("Ошибка %s. Команда %s. Пользователь: %s", e.getMessage(), commandName, userName));
            e.printStackTrace();
        }
    }

}
