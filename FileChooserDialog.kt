package cu.bellalogica.chat

import android.graphics.drawable.Drawable
import android.os.Bundle
import android.os.Environment
import android.support.annotation.ColorRes
import android.support.annotation.DrawableRes
import android.support.v4.app.DialogFragment
import android.support.v4.content.ContextCompat
import android.support.v7.app.AppCompatDialogFragment
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView

import java.io.File
import java.io.FileFilter
import java.util.ArrayList
import java.util.Collections

class FileChooserDialog : AppCompatDialogFragment(), ItemHolder.OnItemClickListener, View.OnClickListener {

    interface ChooserListener {
        /**
         * This method gets called when user selects a file or a directory depending on the chooser type.

         * @param path path of the selected file or directory.
         */
        fun onSelect(path: String)
    }

    enum class ChooserType {
        FILE_CHOOSER,
        DIRECTORY_CHOOSER
    }

    class Builder
    /**
     * Creates a builder for a FileChooser fragment.

     * @param chooserType You can choose to create either a FileChooser or a DirectoryChooser
     */
    (// Required parameters
            private val chooserType: ChooserType, private val chooserListener: ChooserListener) {

        // Optional parameters
        private var fileFormats: Array<String>? = null
        private var multipleFileSelectionEnabled: Boolean = false
        private var title: String? = null
        private var selectDirectoryButtonText: String? = null
        private var initialDirectory: String? = null
        @DrawableRes
        private var fileIconId = R.drawable.ic_file
        @DrawableRes
        private var directoryIconId = R.drawable.ic_directory
        @DrawableRes
        private var previousDirectoryButtonIcon = R.drawable.ic_prev_dir
        @DrawableRes
        private var selectDirectoryButtonBackgroundId: Int = 0
        @ColorRes
        private var selectDirectoryButtonTextColorId: Int = 0
        private var selectDirectoryButtonTextSize: Float = 0.toFloat()

        /**
         * Set the title of this FileChooserDialog.

         * @return This Builder object to allow for chaining of calls to set methods
         */
        fun setTitle(title: String): Builder {
            this.title = title
            return this
        }

        /**
         * Set file formats which are going to be shown by this FileChooserDialog.
         * All types of files will be shown if you don't set it.

         * @param fileFormats A string array of file formats
         * *
         * @return This Builder object to allow for chaining of calls to set methods
         */
        fun setFileFormats(fileFormats: Array<String>): Builder {
            this.fileFormats = fileFormats
            return this
        }

        /**
         * Set whether multiple file selection should be enabled or not.

         * @return This Builder object to allow for chaining of calls to set methods
         */
        fun setMultipleFileSelectionEnabled(enabled: Boolean): Builder {
            this.multipleFileSelectionEnabled = enabled
            return this
        }

        /**
         * Set the initial directory of this FileChooserDialog.

         * @return This Builder object to allow for chaining of calls to set methods
         * *
         * @throws IllegalArgumentException if:
         * *                                  initialDirectory does not exist.
         * *                                  initialDirectory is not a directory.
         * *                                  initialDirectory is not accessible due to access restrictions.
         */
        fun setInitialDirectory(initialDirectory: File?): Builder {
            if (initialDirectory == null)
                throw NullPointerException("initialDirectory can't be null.")

            if (!initialDirectory.exists())
                throw IllegalArgumentException(initialDirectory.path + " Does not exist.")

            if (!initialDirectory.isDirectory)
                throw IllegalArgumentException(initialDirectory.path + " Is not a directory.")

            if (!initialDirectory.canRead())
                throw IllegalArgumentException("Can't access " + initialDirectory.path)

            this.initialDirectory = initialDirectory.path
            return this
        }

        /**
         * Set select directory button's text. You will see this button when chooser type is DIRECTORY_CHOOSER.

         * @return This Builder object to allow for chaining of calls to set methods
         */
        fun setSelectDirectoryButtonText(text: String): Builder {
            this.selectDirectoryButtonText = text
            return this
        }

        /**
         * Set select directory button's text size.

         * @param textSize must be based on scaled pixel(SP) unit.
         * *
         * @return This Builder object to allow for chaining of calls to set methods
         */
        fun setSelectDirectoryButtonTextSize(textSize: Float): Builder {
            selectDirectoryButtonTextSize = textSize
            return this
        }

        /**
         * Set select directory button's text color. You will see this button when chooser type is DIRECTORY_CHOOSER.

         * @return This Builder object to allow for chaining of calls to set methods
         */
        fun setSelectDirectoryButtonTextColor(@ColorRes colorId: Int): Builder {
            selectDirectoryButtonTextColorId = colorId
            return this
        }

        /**
         * Set select directory button's background. You will see this button when chooser type is DIRECTORY_CHOOSER.

         * @return This Builder object to allow for chaining of calls to set methods
         */
        fun setSelectDirectoryButtonBackground(@DrawableRes backgroundId: Int): Builder {
            selectDirectoryButtonBackgroundId = backgroundId
            return this
        }

        /**
         * Set the icon for files in this FileChooserDialog's list
         * Default icon will be used if you don't set it.

         * @return This Builder object to allow for chaining of calls to set methods
         */
        fun setFileIcon(@DrawableRes iconId: Int): Builder {
            fileIconId = iconId
            return this
        }

        /**
         * Set the icon for directories in this FileChooserDialog's list.
         * Default icon will be used if you don't set it.

         * @return This Builder object to allow for chaining of calls to set methods
         */
        fun setDirectoryIcon(@DrawableRes iconId: Int): Builder {
            directoryIconId = iconId
            return this
        }

        /**
         * Set the icon for the button that is going to be used to go to the parent of the current directory.
         * Default icon will be used if you don't set it.

         * @return This Builder object to allow for chaining of calls to set methods
         */
        fun setPreviousDirectoryButtonIcon(@DrawableRes iconId: Int): Builder {
            previousDirectoryButtonIcon = iconId
            return this
        }

        /**
         * Returns an instance of FileChooserDialog with the given configurations.

         * @throws ExternalStorageNotAvailableException If there is no external storage available on user's device
         */
        @Throws(ExternalStorageNotAvailableException::class)
        fun build(): FileChooserDialog {
            val externalStorageState = Environment.getExternalStorageState()
            val externalStorageAvailable = externalStorageState == Environment.MEDIA_MOUNTED || externalStorageState == Environment.MEDIA_MOUNTED_READ_ONLY
            if (!externalStorageAvailable) {
                throw ExternalStorageNotAvailableException()
            }

            val fragment = FileChooserDialog()

            val args = Bundle()
            args.putSerializable(KEY_CHOOSER_TYPE, chooserType)
            fragment.chooserListener = chooserListener
            args.putString(KEY_TITLE, title)
            args.putStringArray(KEY_FILE_FORMATS, fileFormats)
            args.putBoolean(KEY_MULTIPLE_FILE_SELECTION_ENABLED, multipleFileSelectionEnabled)
            args.putString(KEY_INITIAL_DIRECTORY, initialDirectory)
            args.putString(KEY_SELECT_DIRECTORY_BUTTON_TEXT, selectDirectoryButtonText)
            args.putFloat(KEY_SELECT_DIRECTORY_BUTTON_TEXT_SIZE, selectDirectoryButtonTextSize)
            args.putInt(KEY_SELECT_DIRECTORY_BUTTON_TEXT_COLOR_ID, selectDirectoryButtonTextColorId)
            args.putInt(KEY_SELECT_DIRECTORY_BUTTON_BACKGROUND_ID, selectDirectoryButtonBackgroundId)
            args.putInt(KEY_FILE_ICON_ID, fileIconId)
            args.putInt(KEY_DIRECTORY_ICON_ID, directoryIconId)
            args.putInt(KEY_PREVIOUS_DIRECTORY_BUTTON_ICON_ID, previousDirectoryButtonIcon)

            fragment.arguments = args

            return fragment
        }
    }

    private var btnPrevDirectory: Button? = null
    private var btnSelectDirectory: Button? = null
    private var rvItems: RecyclerView? = null
    private var tvCurrentDirectory: TextView? = null
    private var chooserType: ChooserType? = null
    private var chooserListener: ChooserListener? = null
    private var itemsAdapter: ItemsAdapter? = null
    private var fileFormats: Array<String>? = null
    private var multipleFileSelectionEnabled: Boolean = false
    private var currentDirectoryPath: String? = null
    private var title: String? = null
    private var initialDirectory: String? = null
    private var selectDirectoryButtonText: String? = null
    @DrawableRes
    private var directoryIconId: Int = 0
    @DrawableRes
    private var fileIconId: Int = 0
    @DrawableRes
    private var previousDirectoryButtonIconId: Int = 0
    @DrawableRes
    private var selectDirectoryButtonBackgroundId: Int = 0
    @ColorRes
    private var selectDirectoryButtonTextColorId: Int = 0
    private var selectDirectoryButtonTextSize: Float = 0.toFloat()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        getGivenArguments()
        if (title == null) {
            // Remove dialog's title
            // Since setting the style after the fragment is created doesn't have any effect
            // so we have to decide about dialog's title here.
            setStyle(DialogFragment.STYLE_NO_TITLE, 0)
        }
    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater!!.inflate(R.layout.fragment_chooser, container, false)
        findViews(view)
        setListeners()
        dialog.setTitle(title)

        if (chooserType == ChooserType.DIRECTORY_CHOOSER) {
            btnSelectDirectory!!.visibility = View.VISIBLE
            btnSelectDirectory!!.text = selectDirectoryButtonText

            if (selectDirectoryButtonBackgroundId != 0)
                btnSelectDirectory!!.setBackgroundResource(selectDirectoryButtonBackgroundId)

            if (selectDirectoryButtonTextColorId != 0)
                btnSelectDirectory!!.setTextColor(resources.getColor(selectDirectoryButtonTextColorId))

            if (selectDirectoryButtonTextSize > 0)
                btnSelectDirectory!!.textSize = selectDirectoryButtonTextSize
        }
        btnPrevDirectory!!.setBackgroundResource(previousDirectoryButtonIconId)

        itemsAdapter = ItemsAdapter(this, multipleFileSelectionEnabled)
        rvItems!!.layoutManager = LinearLayoutManager(activity)
        rvItems!!.adapter = itemsAdapter

        loadItems(if (initialDirectory != null) initialDirectory else Environment.getExternalStorageDirectory().path)

        return view
    }

    override fun onItemClick(item: Item) {
        if (item.isDirectory) {
            loadItems(item.path)
        } else {
            chooserListener!!.onSelect(item.path)
            dismiss()
        }
    }

    override fun onClick(view: View) {
        val id = view.id
        if (id == R.id.previous_dir_imagebutton) {
            val parent = File(currentDirectoryPath!!).parentFile
            if (parent != null && parent.canRead()) {
                loadItems(parent.path)
            }
        } else if (id == R.id.select_dir_button) {
            chooserListener!!.onSelect(currentDirectoryPath)
            dismiss()
        }
    }

    private fun loadItems(path: String) {
        currentDirectoryPath = path

        val currentDir = path.substring(path.lastIndexOf(File.separator) + 1)
        tvCurrentDirectory!!.text = currentDir

        val files = File(path).listFiles(FileFilter { file ->
            if (file.canRead()) {
                if (chooserType == ChooserType.FILE_CHOOSER && file.isFile) {
                    if (fileFormats != null && fileFormats!!.size > 0) {
                        for (fileFormat in fileFormats!!) {
                            if (file.name.endsWith(fileFormat)) {
                                return@FileFilter true
                            }
                        }
                        return@FileFilter false
                    }
                    return@FileFilter true
                }
                return@FileFilter file.isDirectory
            }
            false
        })

        val items = ArrayList<Item>()
        for (f in files) {
            val drawableId = if (f.isFile) fileIconId else directoryIconId
            val drawable = ContextCompat.getDrawable(activity.applicationContext, drawableId)
            items.add(Item(f.path, drawable))
        }
        Collections.sort(items)

        itemsAdapter!!.setItems(items)
    }

    private fun getGivenArguments() {
        val args = arguments
        chooserType = args.getSerializable(KEY_CHOOSER_TYPE) as ChooserType
        title = args.getString(KEY_TITLE)
        fileFormats = args.getStringArray(KEY_FILE_FORMATS)
        multipleFileSelectionEnabled = args.getBoolean(KEY_MULTIPLE_FILE_SELECTION_ENABLED)
        initialDirectory = args.getString(KEY_INITIAL_DIRECTORY)
        selectDirectoryButtonText = args.getString(KEY_SELECT_DIRECTORY_BUTTON_TEXT)
        selectDirectoryButtonTextSize = args.getFloat(KEY_SELECT_DIRECTORY_BUTTON_TEXT_SIZE)
        selectDirectoryButtonTextColorId = args.getInt(KEY_SELECT_DIRECTORY_BUTTON_TEXT_COLOR_ID)
        selectDirectoryButtonBackgroundId = args.getInt(KEY_SELECT_DIRECTORY_BUTTON_BACKGROUND_ID)
        fileIconId = args.getInt(KEY_FILE_ICON_ID)
        directoryIconId = args.getInt(KEY_DIRECTORY_ICON_ID)
        previousDirectoryButtonIconId = args.getInt(KEY_PREVIOUS_DIRECTORY_BUTTON_ICON_ID)
    }

    private fun setListeners() {
        btnPrevDirectory!!.setOnClickListener(this)
        btnSelectDirectory!!.setOnClickListener(this)
    }

    private fun findViews(v: View) {
        rvItems = v.findViewById(R.id.items_recyclerview) as RecyclerView
        btnPrevDirectory = v.findViewById(R.id.previous_dir_imagebutton) as Button
        btnSelectDirectory = v.findViewById(R.id.select_dir_button) as Button
        tvCurrentDirectory = v.findViewById(R.id.current_dir_textview) as TextView
    }

    companion object {
        private val KEY_CHOOSER_TYPE = "chooserType"
        private val KEY_CHOOSER_LISTENER = "chooserListener"
        private val KEY_TITLE = "title"
        private val KEY_FILE_FORMATS = "fileFormats"
        private val KEY_MULTIPLE_FILE_SELECTION_ENABLED = "multipleFileSelectionEnabled"
        private val KEY_INITIAL_DIRECTORY = "initialDirectory"
        private val KEY_SELECT_DIRECTORY_BUTTON_TEXT = "selectDirectoryButtonText"
        private val KEY_SELECT_DIRECTORY_BUTTON_TEXT_SIZE = "selectDirectoryButtonTextSize"
        private val KEY_SELECT_DIRECTORY_BUTTON_TEXT_COLOR_ID = "selectDirectoryButtonTextColorId"
        private val KEY_SELECT_DIRECTORY_BUTTON_BACKGROUND_ID = "selectDirectoryButtonBackgroundId"
        private val KEY_FILE_ICON_ID = "fileIconId"
        private val KEY_DIRECTORY_ICON_ID = "directoryIconId"
        private val KEY_PREVIOUS_DIRECTORY_BUTTON_ICON_ID = "previousDirectoryButtonIconId"
    }
}