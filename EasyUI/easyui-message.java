----------------------------
EasyUI-message				|
----------------------------
	# ��Ϣ��ʾ��
	# ������ϵ
		window 
		linkbutton 
		progressbar
 
	# �÷�
		* ��������Ϣ��ʾ
			$.messager.alert('����','������Ϣ');		//��һ������Ϊ����,�ڶ�������Ϊ����
		* ����ȷ����ť
			$.messager.confirm('ȷ��','��ȷ����Ҫɾ����¼��',function(r){ 
				if (r){    
					alert('ȷ��ɾ��');    
				}    
			});  


----------------------------
EasyUI-message����			|
----------------------------
	ok
		* ��ʾOK���ַ���,Ĭ��ֵ����OK
	
	cancel
		* ��ʾcancel���ַ���,Ĭ�Ͼ���Cancel
	

----------------------------
EasyUI-message�¼�			|
----------------------------


----------------------------
EasyUI-message����			|
----------------------------
	$.messager.show
		* ����Ļ���½���ʾһ����Ϣ���ڡ���ѡ�������һ�������õĶ���
			showType	//���彫�����ʾ����Ϣ������ֵ�У�null,slide,fade,show��Ĭ�ϣ�slide��
			showSpeed	//���崰����ʾ�Ĺ���ʱ�䡣Ĭ�ϣ�600���롣
			width		//������Ϣ���ڵĿ��ȡ�Ĭ�ϣ�250px��
			height		//������Ϣ���ڵĸ߶ȡ�Ĭ�ϣ�100px��
			title		//��ͷ�������ʾ�ı����ı���
			msg			//��ʾ����Ϣ�ı���
			style		//������Ϣ������Զ�����ʽ��
			timeout		//�������Ϊ0����Ϣ���彫�����Զ��رգ������û��ر������������ɷ�0��������Ϣ���彫�ڳ�ʱ���Զ��رա�Ĭ�ϣ�4000���롣 

	
	$.messager.alert
		* ��ʾ���洰�ڡ�����
		* ע����Ѳ���,������һ����������.��������д���
			title	//��ͷ�������ʾ�ı����ı���
			msg		//��ʾ����Ϣ�ı���
			icon	//��ʾ��ͼ��ͼ�񡣿���ֵ�У�error,question,info,warning ��
			fn:		//�ڴ��ڹرյ�ʱ�򴥷��ûص������� 
		* demo
			$.messager.alert('����һ������','������ʾ��Ϣ','error',function(){
				//���ȷ����,ִ�еĲ���
			});

	$.messager.confirm
		* һ������ȷ����ȡ���Ĵ���
		* ����,������һ����������.��������д��
			title	//��ͷ�������ʾ�ı����ı���
			msg		//��ʾ����Ϣ�ı���
			fn(b)	//���û������ȷ������ť��ʱ�����һ��trueֵ���ص����������򴫵�һ��falseֵ�� 
		* demo
			$.messager.confirm('ȷ��','��ȷ����Ҫɾ����¼��',function(r){ 
				if (r){    
					alert('ȷ��ɾ��');    
				}    
			});  
	

	$.messager.prompt
		* ��ʾһ���û����������ı��Ĳ��Ҵ���ȷ�����͡�ȡ������ť����Ϣ���塣
		* ����
			title	//��ͷ�������ʾ�ı����ı���
			msg		//��ʾ����Ϣ�ı���
			fn(val): ���û�����һ��ֵ������ʱ��ִ�еĻص�������//val�����û������ֵ
	
	$.messager.progress
		* ��ʾһ��������Ϣ���塣���Ǽ�װȡ���ݵ�ʱ�������
		* ����,���������һ������
			title	//��ͷ�������ʾ�ı����ı���Ĭ�ϣ��ա�
			msg		//��ʾ����Ϣ�ı���Ĭ�ϣ��ա� 
			text	//�ڽ���������ʾ���ı���Ĭ�ϣ�undefined��
			interval//ÿ�ν��ȸ��µļ��ʱ�䡣Ĭ�ϣ�300���롣
		
		* ����
			bar			//��ȡ����������
			close		//�رս��ȴ��ڡ�
		
		* demo
			$.messager.progress();			//��ʾ���ȴ���

			$.messager.progress('close');	//�رմ���





		
