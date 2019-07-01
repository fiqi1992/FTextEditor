package editor.visual

import editor.backend.*
import editor.*
import java.awt.Dimension
import java.awt.event.ActionEvent
import javax.swing.ImageIcon
import javax.swing.JMenu
import javax.swing.JMenuBar
import javax.swing.JMenuItem

/**
 * Created by Dima on 22-Aug-15.
 * Updated by FiqiDev on 29-Juni-2019
 *
 */
fun buildMenuBar(): JMenuBar {

    //setup icon - about
    val iconAboutMe = ImageIcon("icons/about_me.png")
    val iconAboutApplication = ImageIcon("icons/about.png")

    //setup menu - about
    val aboutMenu = JMenu("About")
    val aboutMe = JMenuItem("About Me", iconAboutMe)
    val aboutAppication = JMenuItem("About Application", iconAboutApplication)

    aboutMenu.add(aboutMe)
    aboutMenu.add(aboutAppication)


    val bar = JMenuBar()
    bar.add(setupMenu("File", mapOf("New" to ::newFile, "Open" to ::openFile, "Save" to ::saveFile)))
    bar.add(setupMenu("Build", mapOf("Build & Restart" to ::buildRestart)))
    bar.add(setupMenu("Syntax", mapOf("Java" to ::javaSyntax, "Kotlin" to ::kotlinSyntax)))
    bar.add(aboutMenu)
    return bar
}

private fun setupMenu(name: String, map: Map<String, () -> Unit>): JMenu {
    val menu = JMenu(name)
    for (e in map)
        menu.add(setupButton(e.key, e.value))
    return menu
}

private fun setupButton(name: String, func: () -> Unit): JMenuItem {
    val button = JMenuItem(name)
    button.preferredSize = Dimension(200, 25)
    button.addActionListener({ e: ActionEvent ->
        run {
            if (e.source == button) {
                func()
            }
        }
    })
    return button
}