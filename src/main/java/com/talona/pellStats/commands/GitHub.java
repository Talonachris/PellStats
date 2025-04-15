package com.talona.pellStats.commands;

import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.chat.ClickEvent;
import net.md_5.bungee.api.chat.HoverEvent;
import net.md_5.bungee.api.chat.TextComponent;
import net.md_5.bungee.api.chat.hover.content.Text;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import com.talona.pellStats.Strings;

public class GitHub implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
        TextComponent prefixComponent = new TextComponent(Strings.PREFIX + ChatColor.BLUE + "GitHub: ");
        TextComponent linkComponent = new TextComponent(ChatColor.YELLOW + "" + ChatColor.BOLD + "Klick me!");

        HoverEvent hoverEvent = new HoverEvent(HoverEvent.Action.SHOW_TEXT, new Text("Github.com/Talonachris/PellStats"));
        linkComponent.setHoverEvent(hoverEvent);

        ClickEvent clickEvent = new ClickEvent(ClickEvent.Action.OPEN_URL, "https://github.com/Talonachris/PellStats");
        linkComponent.setClickEvent(clickEvent);

        prefixComponent.addExtra(linkComponent);

        commandSender.spigot().sendMessage(prefixComponent);
        return true;
    }
}