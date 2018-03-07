<select name="status" class="input-xlarge required">
	<c:forEach items="${fns:getDictList('product_status')}" var="item">
		<c:choose> 
			<c:when test="${mtProduct.status == item.value}">
				<option value ="${item.value}" selected="selected">${item.label}</option>				
			</c:when> 
			<c:otherwise>
				<c:if test="${mtProduct.status == 0}">
					<c:if test="${item.value == 1}">
						<option value ="${item.value}">${item.label}</option>
					</c:if>
					<c:if test="${item.value == 2}">
						<option value ="${item.value}">${item.label}</option>
					</c:if>
				</c:if>
				<c:if test="${mtProduct.status == 1}">
					<c:if test="${item.value == 0}">
						<option value ="${item.value}">${item.label}</option>
					</c:if>
					<c:if test="${item.value == 2}">
						<option value ="${item.value}">${item.label}</option>
					</c:if>
				</c:if>
				<c:if test="${mtProduct.status == 2}">
					<c:if test="${item.value == 0}">
						<option value ="${item.value}">${item.label}</option>
					</c:if>
					<c:if test="${item.value == 1}">
						<option value ="${item.value}">${item.label}</option>
					</c:if>
				</c:if>
				<c:if test="${mtProduct.status == 3}">
					<c:if test="${item.value == 4}">
						<option value ="${item.value}">${item.label}</option>
					</c:if>
					<c:if test="${item.value == 10}">
						<option value ="${item.value}">${item.label}</option>
					</c:if>
				</c:if>
				<c:if test="${mtProduct.status == 4}">
				
				</c:if>
				<c:if test="${mtProduct.status == 5}">
				
				</c:if>
				<c:if test="${mtProduct.status == 6}">
					<c:if test="${item.value == 7}">
						<option value ="${item.value}">${item.label}</option>
					</c:if>
					<c:if test="${item.value == 8}">
						<option value ="${item.value}">${item.label}</option>
					</c:if>
					<c:if test="${item.value == 9}">
						<option value ="${item.value}">${item.label}</option>
					</c:if>
				</c:if>
				<c:if test="${mtProduct.status == 7}">
					<c:if test="${item.value == 6}">
						<option value ="${item.value}">${item.label}</option>
					</c:if>
					<c:if test="${item.value == 8}">
						<option value ="${item.value}">${item.label}</option>
					</c:if>
					<c:if test="${item.value == 9}">
						<option value ="${item.value}">${item.label}</option>
					</c:if>
				</c:if>
				<c:if test="${mtProduct.status == 8}">
					<c:if test="${item.value == 6}">
						<option value ="${item.value}">${item.label}</option>
					</c:if>
					<c:if test="${item.value == 7}">
						<option value ="${item.value}">${item.label}</option>
					</c:if>
					<c:if test="${item.value == 9}">
						<option value ="${item.value}">${item.label}</option>
					</c:if>
				</c:if>
				<c:if test="${mtProduct.status == 9}">
				
				</c:if>
				<c:if test="${mtProduct.status == 10}">
					<c:if test="${item.value == 4}">
						<option value ="${item.value}">${item.label}</option>
					</c:if>
					<c:if test="${item.value == 11}">
						<option value ="${item.value}">${item.label}</option>
					</c:if>
				</c:if>
				<c:if test="${mtProduct.status == 11}">
				
				</c:if>
			</c:otherwise> 
		</c:choose>
	</c:forEach>
</select>