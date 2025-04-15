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
import org.bukkit.entity.Player;

public class GitHub implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (command.getName().equalsIgnoreCase("pellgit")) {
            if (sender instanceof Player) {
                Player player = (Player) sender;

                if (!player.hasPermission("pellstats.brandhide")) {
                    TextComponent prefixComponent = new TextComponent(Strings.PREFIX + ChatColor.BLUE + "GitHub: ");
                    TextComponent linkComponent = new TextComponent(ChatColor.YELLOW + "" + ChatColor.BOLD + "Klick me!");

                    HoverEvent hoverEvent = new HoverEvent(HoverEvent.Action.SHOW_TEXT, new Text("Github.com/Talonachris/PellStats"));
                    linkComponent.setHoverEvent(hoverEvent);

                    ClickEvent clickEvent = new ClickEvent(ClickEvent.Action.OPEN_URL, "https://github.com/Talonachris/PellStats");
                    linkComponent.setClickEvent(clickEvent);

                    prefixComponent.addExtra(linkComponent);

                    player.spigot().sendMessage(prefixComponent);
                    return false;
                }

                if (player.isOp()) {
                    TextComponent prefixComponent = new TextComponent(Strings.PREFIX + ChatColor.BLUE + "GitHub: ");
                    TextComponent linkComponent = new TextComponent(ChatColor.YELLOW + "" + ChatColor.BOLD + "Klick me!");

                    HoverEvent hoverEvent = new HoverEvent(HoverEvent.Action.SHOW_TEXT, new Text("Github.com/Talonachris/PellStats"));
                    linkComponent.setHoverEvent(hoverEvent);

                    ClickEvent clickEvent = new ClickEvent(ClickEvent.Action.OPEN_URL, "https://github.com/Talonachris/PellStats");
                    linkComponent.setClickEvent(clickEvent);

                    prefixComponent.addExtra(linkComponent);

                    player.spigot().sendMessage(prefixComponent);
                    return false;
                }
                if (player.hasPermission("pellstats.githide")) {
                    player.sendMessage(ChatColor.RED + "You are not permitted to use this command!");
                    return false;
                }
            }
        }
        return true;
    }
}