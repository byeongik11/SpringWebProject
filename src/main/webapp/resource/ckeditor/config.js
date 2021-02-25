/**
 * @license Copyright (c) 2003-2019, CKSource - Frederico Knabben. All rights reserved.
 * For licensing, see https://ckeditor.com/legal/ckeditor-oss-license
 */

CKEDITOR.editorConfig = function( config ) {
	config.height = '500px';
	
	config.toolbarGroups = [
		{ name: 'document', groups: [ 'mode', 'document', 'doctools' ] },
		{ name: 'clipboard', groups: [ 'clipboard', 'undo' ] },
		{ name: 'editing', groups: [ 'find', 'selection', 'spellchecker', 'editing' ] },
		{ name: 'forms', groups: [ 'forms' ] },
		'/',
		{ name: 'basicstyles', groups: [ 'basicstyles', 'cleanup' ] },
		{ name: 'paragraph', groups: [ 'list', 'indent', 'blocks', 'align', 'bidi', 'paragraph' ] },
		{ name: 'links', groups: [ 'links' ] },
		{ name: 'insert', groups: [ 'insert' ] },
		'/',
		{ name: 'styles', groups: [ 'styles' ] },
		{ name: 'colors', groups: [ 'colors' ] },
		{ name: 'tools', groups: [ 'tools' ] },
		{ name: 'others', groups: [ 'others' ] },
		{ name: 'about', groups: [ 'about' ] } 
	];
	
	config.removePlugins = 'image';
	config.extraPlugins = 'image2';
	config.removeButtons = 'Source,Save,NewPage,Preview,Print,Templates,Cut,Copy,Undo,Redo,Replace,Find,Scayt,SelectAll,Paste,PasteText,PasteFromWord,Form,Checkbox,Radio,TextField,Textarea,Select,Button,ImageButton,HiddenField';
};

CKEDITOR.on( 'dialogDefinition', function( ev ){
	 
    var dialogName = ev.data.name;
    var dialogDefinition = ev.data.definition;
    
    if ( dialogName == 'image' ){
        
        dialogDefinition.removeContents( 'Link' );    //링크 탭 제거
        dialogDefinition.removeContents( 'advanced' );  //상세정보 탭 제거
        
        var infoTab = dialogDefinition.getContents( 'info' );  //info탭을 제거하면 이미지 업로드가 안된다.
        
        infoTab.remove( 'txtHSpace'); //info 탭 내에 불필요한 엘레멘트들 제거
        infoTab.remove( 'txtVSpace');
        infoTab.remove( 'txtBorder');
        infoTab.remove( 'txtWidth');
        infoTab.remove( 'txtHeight');
        infoTab.remove( 'ratioLock');
        
    }
});