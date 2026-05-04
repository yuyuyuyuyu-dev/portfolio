package dev.yuyuyuyuyu.portfolio.data.repositories

import dev.yuyuyuyuyu.portfolio.data.models.App
import dev.yuyuyuyuyu.portfolio.data.models.TechStack
import me.tatarka.inject.annotations.Inject
import portfolio.composeapp.generated.resources.CodeScanner_screenshot
import portfolio.composeapp.generated.resources.LocationRemover_screenshot
import portfolio.composeapp.generated.resources.PasswordGenerator_screenshot
import portfolio.composeapp.generated.resources.Res
import portfolio.composeapp.generated.resources.app_desc_barometer
import portfolio.composeapp.generated.resources.app_desc_codescanner
import portfolio.composeapp.generated.resources.app_desc_delicioushare
import portfolio.composeapp.generated.resources.app_desc_digitalclock
import portfolio.composeapp.generated.resources.app_desc_genkaikor
import portfolio.composeapp.generated.resources.app_desc_getrandomint
import portfolio.composeapp.generated.resources.app_desc_howoldami
import portfolio.composeapp.generated.resources.app_desc_inputsourcemanager
import portfolio.composeapp.generated.resources.app_desc_locationremover
import portfolio.composeapp.generated.resources.app_desc_notpullingcalc
import portfolio.composeapp.generated.resources.app_desc_passwordgenerator
import portfolio.composeapp.generated.resources.app_desc_puremusicplayer
import portfolio.composeapp.generated.resources.app_desc_repeater
import portfolio.composeapp.generated.resources.app_desc_toleet
import portfolio.composeapp.generated.resources.app_desc_whatisthedatetoday
import portfolio.composeapp.generated.resources.app_desc_ympreviewer
import portfolio.composeapp.generated.resources.app_mot_barometer
import portfolio.composeapp.generated.resources.app_mot_codescanner
import portfolio.composeapp.generated.resources.app_mot_getrandomint
import portfolio.composeapp.generated.resources.app_mot_howoldami
import portfolio.composeapp.generated.resources.app_mot_inputsourcemanager
import portfolio.composeapp.generated.resources.app_mot_locationremover
import portfolio.composeapp.generated.resources.app_mot_notpullingcalc
import portfolio.composeapp.generated.resources.app_mot_passwordgenerator
import portfolio.composeapp.generated.resources.app_mot_puremusicplayer
import portfolio.composeapp.generated.resources.app_mot_whatisthedatetoday
import portfolio.composeapp.generated.resources.app_name_barometer
import portfolio.composeapp.generated.resources.app_name_codescanner
import portfolio.composeapp.generated.resources.app_name_getrandomint
import portfolio.composeapp.generated.resources.app_name_howoldami
import portfolio.composeapp.generated.resources.app_name_notpullingcalc
import portfolio.composeapp.generated.resources.app_name_passwordgenerator
import portfolio.composeapp.generated.resources.app_name_whatisthedatetoday
import portfolio.composeapp.generated.resources.barometer_en_screenshot
import portfolio.composeapp.generated.resources.barometer_icon
import portfolio.composeapp.generated.resources.barometer_ja_screenshot
import portfolio.composeapp.generated.resources.delicioushareapp_icon
import portfolio.composeapp.generated.resources.genkaikor_screenshot
import portfolio.composeapp.generated.resources.get_random_int_screenshot
import portfolio.composeapp.generated.resources.how_old_am_i_screenshot
import portfolio.composeapp.generated.resources.howoldami_icon
import portfolio.composeapp.generated.resources.locationremover_icon
import portfolio.composeapp.generated.resources.not_pulling_probability_calculator_screenshot
import portfolio.composeapp.generated.resources.puremusicplayer_icon
import portfolio.composeapp.generated.resources.repeater_screenshot
import portfolio.composeapp.generated.resources.to_leet_screenshot
import portfolio.composeapp.generated.resources.what_is_the_date_today_screenshot
import portfolio.composeapp.generated.resources.whatisthedatetoday_icon
import portfolio.composeapp.generated.resources.ym_previewer_screenshot

@Inject
class AppsRepository {
    private val apps =
        listOf(
            App(
                nameFallback = "PureMusicPlayer",
                descriptionRes = Res.string.app_desc_puremusicplayer,
                icon = Res.drawable.puremusicplayer_icon,
                techStack = setOf(TechStack.Ios, TechStack.Swift),
                repositoryUrl = "https://github.com/yuyuyuyuyu-dev/PureMusicPlayer",
                platforms = setOf(dev.yuyuyuyuyu.portfolio.data.models.Platform.Ios),
                motivationRes = Res.string.app_mot_puremusicplayer,
            ),
            App(
                nameFallback = "DigitalClock",
                descriptionRes = Res.string.app_desc_digitalclock,
                techStack = setOf(TechStack.Android, TechStack.Kotlin),
                repositoryUrl = "https://github.com/yuyuyuyuyu-dev/DigitalClock",
                platforms = setOf(dev.yuyuyuyuyu.portfolio.data.models.Platform.Android),
            ),
            App(
                nameRes = Res.string.app_name_whatisthedatetoday,
                descriptionRes = Res.string.app_desc_whatisthedatetoday,
                icon = Res.drawable.whatisthedatetoday_icon,
                techStack =
                    setOf(
                        TechStack.Web,
                        TechStack.Kotlin,
                        TechStack.ComposeMultiplatform,
                        TechStack.Pwa,
                    ),
                url = "https://what-is-the-date-today.yuyuyuyuyu.dev",
                repositoryUrl = "https://github.com/yuyuyuyuyu-dev/what-is-the-date-today",
                screenshots = listOf(Res.drawable.what_is_the_date_today_screenshot),
                platforms = setOf(dev.yuyuyuyuyu.portfolio.data.models.Platform.Web),
                motivationRes = Res.string.app_mot_whatisthedatetoday,
            ),
            App(
                nameRes = Res.string.app_name_howoldami,
                descriptionRes = Res.string.app_desc_howoldami,
                icon = Res.drawable.howoldami_icon,
                techStack =
                    setOf(
                        TechStack.Web,
                        TechStack.Kotlin,
                        TechStack.ComposeMultiplatform,
                        TechStack.Pwa,
                    ),
                url = "https://how-old-am-i.yuyuyuyuyu.dev",
                repositoryUrl = "https://github.com/yuyuyuyuyu-dev/how-old-am-i",
                screenshots = listOf(Res.drawable.how_old_am_i_screenshot),
                platforms = setOf(dev.yuyuyuyuyu.portfolio.data.models.Platform.Web),
                motivationRes = Res.string.app_mot_howoldami,
            ),
            App(
                nameFallback = "Location Remover",
                descriptionRes = Res.string.app_desc_locationremover,
                icon = Res.drawable.locationremover_icon,
                techStack =
                    setOf(
                        TechStack.Web,
                        TechStack.Html,
                        TechStack.Css,
                        TechStack.JavaScript,
                        TechStack.Pwa,
                    ),
                url = "https://yuyuyuyuyu-dev.github.io/location-remover/",
                repositoryUrl = "https://github.com/yuyuyuyuyu-dev/location-remover",
                screenshots = listOf(Res.drawable.LocationRemover_screenshot),
                platforms = setOf(dev.yuyuyuyuyu.portfolio.data.models.Platform.Web),
                motivationRes = Res.string.app_mot_locationremover,
            ),
            App(
                nameRes = Res.string.app_name_getrandomint,
                descriptionRes = Res.string.app_desc_getrandomint,
                techStack =
                    setOf(
                        TechStack.Web,
                        TechStack.TypeScript,
                        TechStack.React,
                        TechStack.MaterialUi,
                    ),
                url = "https://yuyuyuyuyu-dev.github.io/get-random-int/",
                repositoryUrl = "https://github.com/yuyuyuyuyu-dev/get-random-int",
                screenshots = listOf(Res.drawable.get_random_int_screenshot),
                platforms = setOf(dev.yuyuyuyuyu.portfolio.data.models.Platform.Web),
                motivationRes = Res.string.app_mot_getrandomint,
            ),
            App(
                nameFallback = "genkaikor",
                descriptionRes = Res.string.app_desc_genkaikor,
                techStack =
                    setOf(
                        TechStack.Web,
                        TechStack.Kotlin,
                        TechStack.ComposeMultiplatform,
                        TechStack.Pwa,
                    ),
                url = "https://genkaikor.yuyuyuyuyu.dev",
                repositoryUrl = "https://github.com/yuyuyuyuyu-dev/genkaikor",
                screenshots = listOf(Res.drawable.genkaikor_screenshot),
                platforms = setOf(dev.yuyuyuyuyu.portfolio.data.models.Platform.Web),
            ),
            App(
                nameFallback = "repeater",
                descriptionRes = Res.string.app_desc_repeater,
                techStack =
                    setOf(
                        TechStack.Web,
                        TechStack.TypeScript,
                        TechStack.NextJs,
                        TechStack.MaterialUi,
                    ),
                url = "https://yuyuyuyuyu-dev.github.io/repeater/",
                repositoryUrl = "https://github.com/yuyuyuyuyu-dev/repeater",
                screenshots = listOf(Res.drawable.repeater_screenshot),
                platforms = setOf(dev.yuyuyuyuyu.portfolio.data.models.Platform.Web),
            ),
            App(
                nameFallback = "YM previewer for Twitter",
                descriptionRes = Res.string.app_desc_ympreviewer,
                techStack =
                    setOf(
                        TechStack.Web,
                        TechStack.NextJs,
                        TechStack.TypeScript,
                        TechStack.MaterialUi,
                    ),
                url = "https://ym-previewer.vercel.app",
                repositoryUrl = "https://github.com/yuyuyuyuyu-dev/ym-previewer",
                screenshots = listOf(Res.drawable.ym_previewer_screenshot),
                platforms = setOf(dev.yuyuyuyuyu.portfolio.data.models.Platform.Web),
            ),
            App(
                nameFallback = "deliciouShare.app",
                descriptionRes = Res.string.app_desc_delicioushare,
                icon = Res.drawable.delicioushareapp_icon,
                techStack =
                    setOf(
                        TechStack.Web,
                        TechStack.NextJs,
                        TechStack.TypeScript,
                        TechStack.MaterialUi,
                        TechStack.Go,
                        TechStack.Aws,
                        TechStack.AwsLambda,
                        TechStack.AmazonDynamoDb,
                        TechStack.Pwa,
                    ),
                repositoryUrl = "https://github.com/yuyuyuyuyu-dev/deliciouShare",
                platforms = setOf(dev.yuyuyuyuyu.portfolio.data.models.Platform.Web),
            ),
            App(
                nameRes = Res.string.app_name_passwordgenerator,
                descriptionRes = Res.string.app_desc_passwordgenerator,
                techStack =
                    setOf(
                        TechStack.Web,
                        TechStack.NextJs,
                        TechStack.TypeScript,
                        TechStack.MaterialUi,
                    ),
                url = "https://password-generator-yu-ko-ba.vercel.app",
                repositoryUrl = "https://github.com/yuyuyuyuyu-dev/password-generator",
                screenshots = listOf(Res.drawable.PasswordGenerator_screenshot),
                platforms = setOf(dev.yuyuyuyuyu.portfolio.data.models.Platform.Web),
                motivationRes = Res.string.app_mot_passwordgenerator,
            ),
            App(
                nameRes = Res.string.app_name_notpullingcalc,
                descriptionRes = Res.string.app_desc_notpullingcalc,
                techStack =
                    setOf(
                        TechStack.Web,
                        TechStack.Kotlin,
                        TechStack.ComposeMultiplatform,
                        TechStack.Pwa,
                    ),
                url = "https://not-pulling-probability-calculator.yuyuyuyuyu.dev",
                repositoryUrl = "https://github.com/yuyuyuyuyu-dev/not-pulling-probability-calculator",
                screenshots = listOf(Res.drawable.not_pulling_probability_calculator_screenshot),
                platforms = setOf(dev.yuyuyuyuyu.portfolio.data.models.Platform.Web),
                motivationRes = Res.string.app_mot_notpullingcalc,
            ),
            App(
                nameFallback = "ToLeet",
                descriptionRes = Res.string.app_desc_toleet,
                techStack =
                    setOf(
                        TechStack.Web,
                        TechStack.Kotlin,
                        TechStack.ComposeMultiplatform,
                        TechStack.Pwa,
                    ),
                url = "http://to-leet.yuyuyuyuyu.dev/",
                repositoryUrl = "https://github.com/yuyuyuyuyu-dev/to-leet",
                screenshots = listOf(Res.drawable.to_leet_screenshot),
                platforms = setOf(dev.yuyuyuyuyu.portfolio.data.models.Platform.Web),
            ),
            App(
                nameRes = Res.string.app_name_barometer,
                descriptionRes = Res.string.app_desc_barometer,
                icon = Res.drawable.barometer_icon,
                techStack = setOf(TechStack.Android, TechStack.Kotlin, TechStack.JetpackCompose),
                repositoryUrl = "https://github.com/yuyuyuyuyu-dev/barometer",
                screenshots =
                    listOf(
                        Res.drawable.barometer_en_screenshot,
                        Res.drawable.barometer_ja_screenshot,
                    ),
                platforms = setOf(dev.yuyuyuyuyu.portfolio.data.models.Platform.Android),
                motivationRes = Res.string.app_mot_barometer,
            ),
            App(
                nameRes = Res.string.app_name_codescanner,
                descriptionRes = Res.string.app_desc_codescanner,
                techStack = setOf(TechStack.Android, TechStack.Kotlin, TechStack.JetpackCompose),
                repositoryUrl = "https://github.com/yuyuyuyuyu-dev/CodeScanner",
                screenshots = listOf(Res.drawable.CodeScanner_screenshot),
                platforms = setOf(dev.yuyuyuyuyu.portfolio.data.models.Platform.Android),
                motivationRes = Res.string.app_mot_codescanner,
            ),
            App(
                nameFallback = "Input Source Handler",
                descriptionRes = Res.string.app_desc_inputsourcemanager,
                techStack = setOf(TechStack.Swift, TechStack.SwiftUi),
                repositoryUrl = "https://github.com/yuyuyuyuyu-dev/input-source-handler",
                platforms = setOf(dev.yuyuyuyuyu.portfolio.data.models.Platform.MacOS),
                motivationRes = Res.string.app_mot_inputsourcemanager,
                installCommand = "brew tap yuyuyuyuyu-dev/tap\nbrew install --cask input-source-handler",
            ),
        ).sortedBy { it.nameFallback }

    fun getApps() = apps
}
